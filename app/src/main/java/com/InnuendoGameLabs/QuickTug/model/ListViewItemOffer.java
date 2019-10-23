package com.InnuendoGameLabs.QuickTug.model;


public class ListViewItemOffer {
	
	String title;
	int icon;
	public ListViewItemOffer(String _title, int _icon){
		this.title = _title;
		this.icon = _icon;
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
}
