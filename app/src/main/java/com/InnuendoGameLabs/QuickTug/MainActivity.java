package com.InnuendoGameLabs.QuickTug;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ironsource.mediationsdk.IronSource;

import com.InnuendoGameLabs.QuickTug.adapter.TabsPagerAdapter;
import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.UserFunctions;

import org.json.JSONObject;

import java.util.Hashtable;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity implements TabListener {

	boolean isError = false;
	ProgressDialog pd;
	Menu menu;
	ViewPager viewPager;
	TabsPagerAdapter adapter;
	ActionBar actionBar;
	DatabaseHandler db;
	String[] tabTitle = { "Offers", "Rewards", "History", "Invite", "Setting" };
	String coinToAdd = "";

	// Sonic
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_top, menu);
		this.menu = menu;
		updateCoinTitle();
		return true;
	}

	public Menu getMenu() {
		return this.menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			new loadCoins().execute();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.activity_main);
		db = new DatabaseHandler(getApplicationContext());
/*		if(db.getUser().getCountry().equals("VN")){
			Toast.makeText(getApplicationContext(),"We do not allow Vietnamese user !",Toast.LENGTH_LONG).show();
			this.finishAffinity();
		}*/
		//Util.mMediationAgent.initOfferwall(MainActivity.this, GlobalVariables.SONIC_APP_KEY, db.getUser().getId());
		if(GlobalVariables.netList.contains("Sonic")){
			IronSource.init(this, GlobalVariables.SONIC_APP_KEY, IronSource.AD_UNIT.OFFERWALL);
			IronSource.setUserId(db.getUser().getId());
		}
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		actionBar = getActionBar();
		adapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (int i = 0; i < tabTitle.length; i++) {
			actionBar.addTab(actionBar.newTab().setText(tabTitle[i])
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(GlobalVariables.netList.contains("Sonic")){
			IronSource.onPause(this);
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(GlobalVariables.netList.contains("Sonic")){
			IronSource.onResume(this);
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public void updateCoinTitle() {
		MenuItem coinItem = menu.findItem(R.id.menu_coins);
		coinItem.setTitle(GlobalVariables.user.getCoins());
	}

	class loadCoins extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				UserFunctions uf = new UserFunctions();
				JSONObject json = uf.getCoinsByID(GlobalVariables.user.getId());
				String res;
				res = json.getString("success");
				if (Integer.parseInt(res) == 1) {
					isError = false;
					GlobalVariables.user.setCoins(json.getString("coins"));
					DatabaseHandler db = new DatabaseHandler(
							getApplicationContext());
					db.resetTables();
					db.addUser(GlobalVariables.user);
				} else {
					isError = true;
				}

			} catch (Exception e) {
				isError = true;
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("Loading...please wait !");
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			pd.dismiss();
			if (!isError) {
				updateCoinTitle();
			} else {
				Toast.makeText(MainActivity.this.getApplicationContext(),
						"Error ! Please try again later !", Toast.LENGTH_LONG)
						.show();

			}
		}

	}

}
