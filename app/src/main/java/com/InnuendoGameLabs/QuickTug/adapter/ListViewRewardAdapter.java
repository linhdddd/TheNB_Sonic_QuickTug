package com.InnuendoGameLabs.QuickTug.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.InnuendoGameLabs.QuickTug.R;
import com.InnuendoGameLabs.QuickTug.model.ListViewItemReward;

import java.util.List;

public class ListViewRewardAdapter extends BaseAdapter {
	Context context;
	List<ListViewItemReward> listItem;

	public ListViewRewardAdapter(Context context,
			List<ListViewItemReward> listItem) {
		super();
		this.context = context;
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listItem.indexOf(listItem.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.reward_listitem, parent,
					false);
		}
		ImageView iView = (ImageView) convertView.findViewById(R.id.iv_reward);
		TextView tv_title = (TextView) convertView
				.findViewById(R.id.tv_reward_title);
		TextView tv_des = (TextView) convertView
				.findViewById(R.id.tv_reward_des);
		TextView tv_point = (TextView) convertView
				.findViewById(R.id.tv_reward_point);
		TextView tv_cre = (TextView) convertView
				.findViewById(R.id.tv_reward_credit);

		ListViewItemReward viewItem = listItem.get(position);

		iView.setImageResource(viewItem.getIcon());
		tv_title.setText(viewItem.getTitle());
		tv_des.setText(viewItem.getDescription());
		tv_point.setText(viewItem.getPoint());
		tv_cre.setText(viewItem.getCredit());

		return convertView;
	}
}
