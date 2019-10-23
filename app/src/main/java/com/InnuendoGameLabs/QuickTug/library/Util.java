package com.InnuendoGameLabs.QuickTug.library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.Random;

//import com.supersonic.mediationsdk.sdk.Supersonic;

public class Util {
	
	
	
	//public static Supersonic mMediationAgent;

	/*
	public static String getAndroidID(Context context) {
		return android.provider.Settings.Secure.getString(
				context.getContentResolver(), "android_id");
	}

	public static String getDeviceInfo(Context context) {
		String str = "";
		str += getAndroidID(context);
		return str;
	}
	*/

	public static String getGoogleAdvertisingID(Context context){
		try{
			return AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
		}catch (Exception e){
			Log.d("4444444444444444",e.getMessage());
			return "errOnGettingGoogleAdvertisingID";
		}

	}

	/*
	public static void getGoogleAdvertisingID(Context context){
		final Context mContext = context;
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				AdvertisingIdClient.Info idInfo = null;
				try {
					idInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
				} catch (GooglePlayServicesNotAvailableException e) {
					e.printStackTrace();
				} catch (GooglePlayServicesRepairableException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String advertId = null;
				try{
					advertId = idInfo.getId();
				}catch (Exception e){
					e.printStackTrace();
				}
				GlobalVariables.gaid = advertId;
				return advertId;
			}
			@Override
			protected void onPostExecute(String advertId) {
				Toast.makeText(mContext, advertId, Toast.LENGTH_SHORT).show();
			}
		};
		task.execute();
	}
	*/
	public final static int generateRandomNumber() {
			Random ran = new Random();
			int x = ran.nextInt(1999999999);
		return x;
	}

	public static final boolean isEmpty(EditText editText) {
		String target = editText.getText().toString().trim();
		return TextUtils.isEmpty(target);
	}
	public static final boolean isEmpty(TextView textview) {
		String target = textview.getText().toString().trim();
		return TextUtils.isEmpty(target);
	}
	
	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}
	public static boolean isNetAvaible(String name){
		String[] arr = GlobalVariables.netList.split(",");
		for (String s : arr) {
			if(s.equals(name)){
				return true;
			}
		}
		return false;
	}
}
