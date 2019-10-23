package com.InnuendoGameLabs.QuickTug.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.InnuendoGameLabs.QuickTug.FragmentHistory;
import com.InnuendoGameLabs.QuickTug.FragmentInvite;
import com.InnuendoGameLabs.QuickTug.FragmentOffers;
import com.InnuendoGameLabs.QuickTug.FragmentSetting;
import com.InnuendoGameLabs.QuickTug.RewardFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new FragmentOffers();
		case 1:
			return new RewardFragment();
		case 2:
			return new FragmentHistory();
		case 3:
			return new FragmentInvite();
		case 4:
			return new FragmentSetting();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 5;
	}

}
