package com.InnuendoGameLabs.QuickTug.model;

public class User {

	private String id;
	private String email;
	private String created_at;
	private String coins;
	private String gaid;
	// first constructor
	public User() {
		super();
	}

	/*
	public User(String id, String email, String created_at,  String coins, String gaid) {
		this.id = id;
		this.email = email;
		this.created_at = created_at;
		this.coins = coins;
		this.gaid = gaid;
	}
*/
	public User(String email, String created_at, String coins, String gaid) {
		this.email = email;
		this.created_at = created_at;
		this.coins = coins;
		this.gaid = gaid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCoins() {
		return coins;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public String getGaid() {
		return gaid;
	}

	public void setGaid(String gaid) {
		this.gaid = gaid;
	}

}
