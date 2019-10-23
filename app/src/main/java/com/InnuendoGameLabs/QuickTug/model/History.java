package com.InnuendoGameLabs.QuickTug.model;

public class History {

	String userID;
	String offer_name;
	String payout;
	String payout_in_usd;
	String network_name;
	String date;


	public History(String userID, String offer_name, String payout, String payout_in_usd, String network_name, String date) {
		this.userID = userID;
		this.offer_name = offer_name;
		this.payout = payout;
		this.payout_in_usd = payout_in_usd;
		this.network_name = network_name;
		this.date = date;
	}

	public History(String offer_name, String payout, String network_name, String date) {
		this.offer_name = offer_name;
		this.payout = payout;
		this.network_name = network_name;
		this.date = date;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getOffer_name() {
		return offer_name;
	}

	public void setOffer_name(String offer_name) {
		this.offer_name = offer_name;
	}

	public String getPayout() {
		return payout;
	}

	public void setPayout(String payout) {
		this.payout = payout;
	}

	public String getPayout_in_usd() {
		return payout_in_usd;
	}

	public void setPayout_in_usd(String payout_in_usd) {
		this.payout_in_usd = payout_in_usd;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
