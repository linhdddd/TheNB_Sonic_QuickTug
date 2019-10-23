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
import com.InnuendoGameLabs.QuickTug.model.ListViewItemOffer;

import java.util.List;

public class ListViewOfferAdapter extends BaseAdapter {

	Context context;
	List<ListViewItemOffer> listItem;

	public ListViewOfferAdapter(Context context, List<ListViewItemOffer> listItem) {
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
			convertView = inflater.inflate(R.layout.offer_listitem, parent,
					false);
		}
		ImageView iView = (ImageView) convertView.findViewById(R.id.iv1);
		TextView textView = (TextView) convertView.findViewById(R.id.tv1);

		ListViewItemOffer viewItem = listItem.get(position);

		iView.setImageResource(viewItem.getIcon());
		textView.setText(viewItem.getTitle());

		return convertView;
	}

}
