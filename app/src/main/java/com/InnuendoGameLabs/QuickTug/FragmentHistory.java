package com.InnuendoGameLabs.QuickTug;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.InnuendoGameLabs.QuickTug.adapter.ListViewRewardAdapter;
import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.UserFunctions;
import com.InnuendoGameLabs.QuickTug.model.History;
import com.InnuendoGameLabs.QuickTug.model.ListViewItemReward;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

	ProgressDialog dialog;
	ListView listview;
	String[] titles;
	TypedArray icons;
	String[] point;
	String[] date;
	String[] cre;
	ListViewRewardAdapter listViewAdapter;
	List<ListViewItemReward> listItem;
	ArrayList<History> arrHistory;
	boolean isError = false;
	DatabaseHandler db;
	private WeakReference<loadHistory> asWeakReference;

	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_history, container, false);
		listview = (ListView) v.findViewById(R.id.lv_history);
		db = new DatabaseHandler(getActivity().getApplicationContext());
		icons = getActivity().getResources().obtainTypedArray(
				R.array.listview_icon_reward);
		listItem = new ArrayList<ListViewItemReward>();
		startLoadHistory();
		return v;
	}

	private void startLoadHistory() {
		loadHistory lHistory = new loadHistory(this);
		this.asWeakReference = new WeakReference<loadHistory>(
				lHistory);
		lHistory.execute();
	}

	class loadHistory extends AsyncTask<String, String, String> {
		private WeakReference<FragmentHistory> reference;

		private loadHistory(FragmentHistory fragmentHistory) {
			this.reference = new WeakReference<FragmentHistory>(fragmentHistory);
		}

		@Override
		protected String doInBackground(String... params) {
			arrHistory = new ArrayList<History>();
			History tempHistory = null;
			UserFunctions uf = new UserFunctions();
			JSONArray jsonArray = uf.loadHistory(GlobalVariables.user.getId());
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject c = jsonArray.getJSONObject(i);
					tempHistory = new History(c.getString("OfferName"), c.getString("Payout"), c.getString("NetworkName"), c.getString("StrDate"));
					arrHistory.add(tempHistory);
				}
				isError = false;
			} catch (Exception e) {
				e.printStackTrace();
				isError = true;
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage("Loading...Please wait !");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			for (History h : arrHistory) {
					ListViewItemReward item = new ListViewItemReward();

					item.setTitle(h.getOffer_name());
					item.setDescription(h.getDate());
					item.setCredit("Coins");
					item.setPoint(h.getPayout());

					if (h.getNetwork_name().equals("Fyber")) {
						item.setIcon(R.drawable.ic_fyber);
					} else {
						item.setIcon(R.mipmap.ic_launcher);
					}
					listItem.add(item);
			}
			listViewAdapter = new ListViewRewardAdapter(getActivity()
					.getApplicationContext(), listItem);
			listview.setAdapter(listViewAdapter);
		}

	}
}
