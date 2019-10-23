package com.InnuendoGameLabs.QuickTug;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.UserFunctions;
import com.InnuendoGameLabs.QuickTug.library.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity {
	EditText etEmail;
	Button btnSubmit;
	ProgressDialog pd;
	UserFunctions userFunctions;
	JSONObject json;
	String email;
	String flag = "";
	int errorType = 0;
	boolean isError = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		etEmail = (EditText) findViewById(R.id.ed_register_email);
		btnSubmit = (Button) findViewById(R.id.bt_register_submit);

		if(!GlobalVariables.isReg){
			new doRegister().execute();
		}else{
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (Util.isEmpty(etEmail) || Util.isEmpty(etEmail)) {
						Toast.makeText(RegisterActivity.this,
								getString(R.string.fill_all_field),
								Toast.LENGTH_LONG).show();
					} else{
						email = etEmail.getText().toString();
						new doRegister().execute();
					}
				}
			});
		}

	}

	class doRegister extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			JSONObject json_user;
			try {

				userFunctions = new UserFunctions();
				if(!GlobalVariables.isReg){
					Long tsLong = System.currentTimeMillis()/1000;
					String ts = tsLong.toString();
					json = userFunctions.signUp(GlobalVariables.NHAN_VIEN+ts, GlobalVariables.gaid);
				}else{
					json = userFunctions.signUp(email, GlobalVariables.gaid);
				}
				try {
					if (Integer.parseInt(json.getString("success")) == 1) {
						flag = "regOK";
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
					} else {
						flag = "regError";
						errorType = Integer.parseInt(json.getString("error"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			} catch (Exception e) {
				isError = true;
				return null;
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(RegisterActivity.this);
			pd.setMessage("Loading...please wait !");
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();

		}

		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();
			if (isError) {
				Toast.makeText(RegisterActivity.this,
						"Error ! Check your internet and information !",
						Toast.LENGTH_LONG).show();
			} else {
				if (flag.equals("regOK")) {
					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(i);
					finish();
				} else if (flag.equals("regError")) {
					switch (errorType) {
						case 1:
							Toast.makeText(RegisterActivity.this,
									getString(R.string.register_error_1),
									Toast.LENGTH_LONG).show();
							break;
						case 2:
							Toast.makeText(RegisterActivity.this,
									getString(R.string.register_error_2),
									Toast.LENGTH_LONG).show();
							break;
						case 3:
							Toast.makeText(RegisterActivity.this,
									getString(R.string.register_error_3),
									Toast.LENGTH_LONG).show();
							break;
						default:
							break;
					}
				}

			}
		}
	}
}
