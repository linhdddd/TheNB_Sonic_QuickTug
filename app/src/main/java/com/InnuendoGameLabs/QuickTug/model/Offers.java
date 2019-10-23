package com.InnuendoGameLabs.QuickTug.model;

public class Offers {
	private String name;
	private String url;
	private String imgURL;
	private String payout;
	private String network_name;

	public Offers(String name, String url, String imgURL, String payout, String network_name) {
		this.name = name;
		this.url = url;
		this.imgURL = imgURL;
		this.payout = payout;
		this.network_name = network_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getPayout() {
		return payout;
	}

	public void setPayout(String payout) {
		this.payout = payout;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}
}
