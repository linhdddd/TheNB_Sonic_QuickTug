package com.InnuendoGameLabs.QuickTug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;

public class FragmentInvite extends Fragment {
	TextView tvMyCode;
	Button btnSubmit;
	EditText etInvited;
	TextView tvAfter;
	DatabaseHandler db;
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_invite, container, false);
		db=new DatabaseHandler(container.getContext());
		tvMyCode = (TextView) v.findViewById(R.id.tv_my_invite);
		tvMyCode.setText(GlobalVariables.user.getId());
		etInvited = (EditText) v.findViewById(R.id.ed_invite);
		btnSubmit = (Button) v.findViewById(R.id.bt_Invite);
		tvAfter = (TextView) v.findViewById(R.id.tv_invite_after_input);


		return v;

	}




}
