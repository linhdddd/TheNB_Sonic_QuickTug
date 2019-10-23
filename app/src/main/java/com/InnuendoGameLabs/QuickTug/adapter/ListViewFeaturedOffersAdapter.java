package com.InnuendoGameLabs.QuickTug.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import com.InnuendoGameLabs.QuickTug.R;
import com.InnuendoGameLabs.QuickTug.model.Offers;

import java.util.List;

public class ListViewFeaturedOffersAdapter extends BaseAdapter {
	Context context;
	List<Offers> listItem;

	public ListViewFeaturedOffersAdapter(Context context,
			List<Offers> listItem) {
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

		Offers viewItem = listItem.get(position);

		//iView.setImageResource(viewItem.getIcon());
		try{
			Picasso.with(context).load(viewItem.getImgURL()).into(iView);
		}catch (Exception e){
			Picasso.with(context).load(R.drawable.ic_hoicham).into(iView);
		}
		
		
		tv_title.setText(viewItem.getName());
		tv_des.setText(viewItem.getNetwork_name());
		tv_point.setText(viewItem.getPayout());
		tv_cre.setText("Coins");

		return convertView;
	}
}
