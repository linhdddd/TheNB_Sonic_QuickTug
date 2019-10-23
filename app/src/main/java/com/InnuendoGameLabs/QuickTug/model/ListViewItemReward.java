package com.InnuendoGameLabs.QuickTug.model;

public class ListViewItemReward {
	String title;
	int icon;
	String description;
	String point;
	String credit;

	
	
	public ListViewItemReward() {
		super();
	}

	public ListViewItemReward(String _title, int _icon, String _description,
                              String _point, String _credit) {
		this.title = _title;
		this.icon = _icon;
		this.description = _description;
		this.point = _point;
		this.credit = _credit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
}
