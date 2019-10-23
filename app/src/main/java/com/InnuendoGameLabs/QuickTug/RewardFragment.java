package com.InnuendoGameLabs.QuickTug;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.InnuendoGameLabs.QuickTug.adapter.ListViewOfferAdapter;
import com.InnuendoGameLabs.QuickTug.adapter.ListViewRewardAdapter;
import com.InnuendoGameLabs.QuickTug.model.ListViewItemOffer;
import com.InnuendoGameLabs.QuickTug.model.ListViewItemReward;

import java.util.ArrayList;
import java.util.List;

public class RewardFragment extends Fragment {
	MainActivity mainActivity;
	ProgressDialog dialog;
	ListView listview;
	String[] titles;
	String[] point;
	String[] des;
	String[] cre;
	TypedArray icons;
	ListViewOfferAdapter listViewAdapter;
	ListViewRewardAdapter listViewRewardAdapter;
	List<ListViewItemOffer> listItem;
	List<ListViewItemReward> listItemReward;
	List<ListViewItemReward> listViewItemRewards;
	String rewardNameClicked = "";
	int positionClicked = 0;
	int dialogPositionClicked = 0;
	boolean isError = true;
	List<String> listDenomination;
	int errorType = 0;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_reward, container, false);
		mainActivity = (MainActivity) getActivity();
		listview = (ListView) v.findViewById(R.id.lv_reward);
		titles = getActivity().getResources().getStringArray(
				R.array.listview_titles_reward);
		icons = getActivity().getResources().obtainTypedArray(
				R.array.listview_icon_reward);
		des = getActivity().getResources().getStringArray(
				R.array.listview_description_reward);
		cre = getActivity().getResources().getStringArray(
				R.array.listview_credits_reward);
		listItem = new ArrayList<ListViewItemOffer>();

		for (int i = 0; i < titles.length; i++) {
			ListViewItemOffer lItem = new ListViewItemOffer(titles[i],
					icons.getResourceId(i, -1));
			listItem.add(lItem);
		}
		listViewAdapter = new ListViewOfferAdapter(getActivity()
				.getApplicationContext(), listItem);
		listview.setAdapter(listViewAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				positionClicked = position;
				rewardNameClicked = titles[position];
				//check
					//startLoadRewardDetails();
			}

		});
		return v;
	}


}
