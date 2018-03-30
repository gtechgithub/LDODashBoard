package com.implementation;

public class LnkConnectionCheck {

	//unique indentifer
	private String urlIDDesc;
	
	//url link attached to the group
	private String urlIDGroup;

	//actual url 
	private String urlLink;
	
	//url description
	private String urlDescription;
	
	//url user id
	private String userID;
	
	//url password
	private String password;
	
	public String getUrlIDGroup() {
		return urlIDGroup;
	}
	public void setUrlIDGroup(String urlIDGroup) {
		this.urlIDGroup = urlIDGroup;
	}

	public String getUrlIDDesc() {
		return urlIDDesc;
	}
	public void setUrlIDDesc(String urlIDDesc) {
		this.urlIDDesc = urlIDDesc;
	}
	
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public String getUrlDescription() {
		return urlDescription;
	}
	public void setUrlDescription(String urlDescription) {
		this.urlDescription = urlDescription;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
