package com.InnuendoGameLabs.QuickTug;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.library.Util;
import com.InnuendoGameLabs.QuickTug.model.User;


public class FragmentSetting extends Fragment implements OnClickListener {
	TextView tvEmail;
	TextView tvPaypal;
	TextView tvWmz;
	TextView tvPm;
	ImageView imEditEmail;
	ImageView imEditPaypal;
	ImageView imEditWmz;
	ImageView imEditPm;
	Button btnReset;
	Button btnSubmit;
	ProgressDialog dialog;
	User user;
	boolean isError = true;
	String errType = "";

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_setting, container, false);
		 user = GlobalVariables.user;
		tvEmail = (TextView) v.findViewById(R.id.tv_email_edit);
		tvEmail.setText(GlobalVariables.user.getEmail());
		tvPaypal = (TextView) v.findViewById(R.id.tv_paypal_edit);
		//tvPaypal.setText(GlobalVariables.user.getPaypal());
		//tvWmz = (TextView) v.findViewById(R.id.tv_webmoney_edit);
		//tvWmz.setText(GlobalVariables.user.getWebmoney());
		tvPm = (TextView) v.findViewById(R.id.tv_perfect_edit);
		//tvPm.setText(GlobalVariables.user.getPerfectmoney());
		imEditEmail = (ImageView) v.findViewById(R.id.bt_edit_email);
		imEditPaypal = (ImageView) v.findViewById(R.id.bt_edit_paypal);
		imEditWmz = (ImageView) v.findViewById(R.id.bt_edit_wmz);
		imEditPm = (ImageView) v.findViewById(R.id.bt_edit_pm);
		btnReset = (Button) v.findViewById(R.id.bt_submit);
		btnSubmit = (Button) v.findViewById(R.id.bt_reset);
		init();
		return v;
	}

	private void init() {
		imEditEmail.setOnClickListener(this);
		imEditPaypal.setOnClickListener(this);
		imEditWmz.setOnClickListener(this);
		imEditPm.setOnClickListener(this);
		btnReset.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_edit_email: {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View convertView = (View) inflater.inflate(R.layout.dialog, null);
			alertDialog.setView(convertView);
			alertDialog.setTitle("Setting");
			final AlertDialog alert = alertDialog.create();
			TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
			final EditText etName = (EditText) convertView
					.findViewById(R.id.ed_info);
			Button btnSubmit = (Button) convertView.findViewById(R.id.bt_ok);
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					tvEmail.setText(etName.getText().toString());
					alert.dismiss();
				}
			});
			Button btnCancel = (Button) convertView
					.findViewById(R.id.bt_cancel);
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alert.dismiss();
				}
			});
			alert.show();
		}
			break;
		case R.id.bt_edit_paypal: {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View convertView = (View) inflater.inflate(R.layout.dialog, null);
			alertDialog.setView(convertView);
			alertDialog.setTitle("Setting");
			final AlertDialog alert = alertDialog.create();
			TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvName.setText("Paypal Account");
			final EditText etName = (EditText) convertView
					.findViewById(R.id.ed_info);
			Button btnSubmit = (Button) convertView.findViewById(R.id.bt_ok);
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					tvPaypal.setText(etName.getText().toString());
					alert.dismiss();
				}
			});
			Button btnCancel = (Button) convertView
					.findViewById(R.id.bt_cancel);
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alert.dismiss();
				}
			});
			alert.show();
		}
			break;
		case R.id.bt_edit_pm: {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View convertView = (View) inflater.inflate(R.layout.dialog, null);
			alertDialog.setView(convertView);
			alertDialog.setTitle("Setting");
			final AlertDialog alert = alertDialog.create();
			TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvName.setText("Perfect Money Account");
			final EditText etName = (EditText) convertView
					.findViewById(R.id.ed_info);
			Button btnSubmit = (Button) convertView.findViewById(R.id.bt_ok);
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					tvPm.setText(etName.getText().toString());
					alert.dismiss();
				}
			});
			Button btnCancel = (Button) convertView
					.findViewById(R.id.bt_cancel);
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alert.dismiss();
				}
			});
			alert.show();
		}
			break;
		case R.id.bt_edit_wmz: {
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View convertView = (View) inflater.inflate(R.layout.dialog, null);
			alertDialog.setView(convertView);
			alertDialog.setTitle("Setting");
			final AlertDialog alert = alertDialog.create();
			TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvName.setText("Webmoney Account");
			final EditText etName = (EditText) convertView
					.findViewById(R.id.ed_info);
			Button btnSubmit = (Button) convertView.findViewById(R.id.bt_ok);
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					tvWmz.setText(etName.getText().toString());
					alert.dismiss();
				}
			});
			Button btnCancel = (Button) convertView
					.findViewById(R.id.bt_cancel);
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alert.dismiss();
				}
			});
			alert.show();
		}
			break;
		case R.id.bt_reset: {
			//tvEmail.setText(GlobalVariables.user.getEmail());
			//tvPaypal.setText(GlobalVariables.user.getPaypal());
			//tvPm.setText(GlobalVariables.user.getPerfectmoney());
			//tvWmz.setText(GlobalVariables.user.getWebmoney());
		}
			break;
		case R.id.bt_submit: {
			if (Util.isEmpty(tvEmail) || Util.isEmpty(tvPaypal)
					|| Util.isEmpty(tvPm) || Util.isEmpty(tvWmz)) {
				Toast.makeText(getActivity().getApplicationContext(),
						getString(R.string.fill_all_field), Toast.LENGTH_LONG)
						.show();
			} else {
				//user.setEmail(tvEmail.getText().toString());
				//user.setPaypal(tvPaypal.getText().toString());
				//user.setPerfectmoney(tvPm.getText().toString());
				//user.setWebmoney(tvWmz.getText().toString());
				//startChangeSetting();
			}
		}
			break;

		default:
			break;
		}
	}



}
