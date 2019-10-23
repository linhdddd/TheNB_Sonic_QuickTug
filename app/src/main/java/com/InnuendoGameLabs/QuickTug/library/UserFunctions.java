package com.InnuendoGameLabs.QuickTug.library;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class UserFunctions {
	
	private JSONParser jsonParser;

	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	public JSONObject getUser(String gaid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_user_by_device_tag));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject signUp(String email, String gaid)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.register_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject getCoinsByID(String id)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_coins_tag));
		params.add(new BasicNameValuePair("userid", id));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONArray loadHistory(String id)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.load_history_tag));
		params.add(new BasicNameValuePair("userid", id));
		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONArray loadOffers()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("task", GlobalVariables.load_offers_tag));
		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
		return json;
	}


}
