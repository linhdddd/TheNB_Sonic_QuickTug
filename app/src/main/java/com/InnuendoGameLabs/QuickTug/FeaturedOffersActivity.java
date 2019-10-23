package com.InnuendoGameLabs.QuickTug;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.InnuendoGameLabs.QuickTug.adapter.ListViewFeaturedOffersAdapter;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.UserFunctions;
import com.InnuendoGameLabs.QuickTug.library.Util;
import com.InnuendoGameLabs.QuickTug.model.Offers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeaturedOffersActivity extends Activity {
	ProgressDialog dialog;
	ListView lvFeaturedOffers;
	ArrayList<Offers> arrOffers;
	boolean isError = false;
	List<Offers> listItem;
	ListViewFeaturedOffersAdapter adapter;
	List<String> listLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_featured_offers);
		lvFeaturedOffers = (ListView) findViewById(R.id.lvFeaturedOffers);
		listLink = new ArrayList<String>();
		new loadOffers().execute();
	}

	class loadOffers extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			arrOffers = new ArrayList<Offers>();
			Offers tempOffers = null;
			UserFunctions uf = new UserFunctions();
			JSONArray jsonArray = uf.loadOffers();
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject c = jsonArray.getJSONObject(i);
					tempOffers = new Offers(c.getString("Name"),
							c.getString("Url"), c.getString("ImgUrl"),
							c.getString("Payout"), c.getString("NetworkName"));
					arrOffers.add(tempOffers);
					listLink.add(c.getString("Url"));
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
			dialog = new ProgressDialog(FeaturedOffersActivity.this);
			dialog.setMessage("Loading...Please wait !");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			adapter = new ListViewFeaturedOffersAdapter(getApplicationContext(), arrOffers); 
			lvFeaturedOffers.setAdapter(adapter);
			lvFeaturedOffers.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
					String url = listLink.get(position);
					url = url.replace("{USER_ID}", GlobalVariables.user.getId());
					url = url.replace("{RANDOM_NUMBER}", ""+ Util.generateRandomNumber());
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(browserIntent);
				}
			});
		}

	}
}
