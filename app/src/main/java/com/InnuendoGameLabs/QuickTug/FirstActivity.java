package com.InnuendoGameLabs.QuickTug;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;


import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.UserFunctions;
import com.InnuendoGameLabs.QuickTug.library.Util;

import org.json.JSONObject;

public class FirstActivity extends Activity {
	Context context;
	ProgressDialog pd;
	DatabaseHandler db;
	boolean isConnected = false;
	boolean isDeviceRegistered = false;
	boolean isServerError = false; 
	boolean isServerMaintenance = false;
	String appVersion = "0";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);
		context = FirstActivity.this;
		db = new DatabaseHandler(getApplicationContext());
		db.resetTables();
		new check().execute();
	}

	class check extends AsyncTask<String, String, String> {
		JSONObject json_user;

		@Override
		protected void onPostExecute(String result) {
			pd.cancel();
			if (!isConnected) {
				Toast.makeText(context,
						getString(R.string.fistActivity_error_internet),
						Toast.LENGTH_LONG).show();
				finish();
			} else if (isServerError) {
				Toast.makeText(context,
						getString(R.string.fistActivity_error_server),
						Toast.LENGTH_LONG).show();
				finish();
			}
			else if(isServerMaintenance){
				Toast.makeText(context,
						getString(R.string.fistActivity_error_server_maintenance),
						Toast.LENGTH_LONG).show();
				finish();
			}
			else if(Integer.parseInt(appVersion) != GlobalVariables.appVersion){
				Toast.makeText(context,
						getString(R.string.fistActivity_error_app_version),
						Toast.LENGTH_LONG).show();
				
				final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
				try {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
				} catch (android.content.ActivityNotFoundException anfe) {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
				}
				finish();
			}
			else if (isDeviceRegistered) {
					Intent i = new Intent(FirstActivity.this, MainActivity.class);
					startActivity(i);
			} else {
				Intent i = new Intent(FirstActivity.this, RegisterActivity.class);
				startActivity(i);
			}
		} 

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(FirstActivity.this);
			pd.setMessage("Loading...please wait !");
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		@Override
		protected void onProgressUpdate(String... values) {
		}

		@Override
		protected String doInBackground(String... params) {
			isConnected = Util.checkInternetConnection(FirstActivity.this);
			GlobalVariables.gaid=Util.getGoogleAdvertisingID(FirstActivity.this);
			if (!isConnected) {
				return null;
			} else {
				try {
					UserFunctions uf = new UserFunctions();
					JSONObject json = uf.getUser(GlobalVariables.gaid);
					String res = json.getString("success");
					if (Integer.parseInt(res) == 1) {
						isDeviceRegistered = true;
						DatabaseHandler db = new DatabaseHandler(
								getApplicationContext());
						db.resetTables();
						json_user = json.getJSONObject("user");
						GlobalVariables.user.setId(json_user.getString("ID"));
						GlobalVariables.user.setEmail(json_user
								.getString("Email"));
						GlobalVariables.user.setCreated_at(json_user
								.getString("CreateDate"));
						GlobalVariables.user.setCoins(json_user
								.getString("Coins"));
						GlobalVariables.user.setGaid(json_user
								.getString("GAID"));
						db.addUser(GlobalVariables.user);
						appVersion = json.getString("appversion");
					} else {
						if(Integer.parseInt(json.getString("error")) == 1){
							isDeviceRegistered = false;
							appVersion = json.getString("appversion");
						}else{
							isServerMaintenance = true;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					isServerError = false;
				}
				return null;
			}
		}

	}
}
