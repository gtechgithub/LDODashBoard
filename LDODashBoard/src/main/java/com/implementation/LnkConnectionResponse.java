package com.implementation;

public class LnkConnectionResponse {
	
	//describes the actual url link
	private String urlLink;
	
	//describes the url description
	private String urlDescription;
	
	//it is the url http connection response code
	private int urlResponseCode;
	
	//response code description, what does the response code actually mean
	private String urlResponseDescription;

	//url response status, means ok, not OK
	private String urlResponseStatus;
	
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

	public int getUrlResponseCode() {
		return urlResponseCode;
	}

	public void setUrlResponseCode(int urlResponseCode) {
		this.urlResponseCode = urlResponseCode;
	}

	public String getUrlResponseDescription() {
		return urlResponseDescription;
	}

	public void setUrlResponseDescription(String urlResponseDescription) {
		this.urlResponseDescription = urlResponseDescription;
	}

	public String getUrlResponseStatus() {
		return urlResponseStatus;
	}

	public void setUrlResponseStatus(String urlResponseStatus) {
		this.urlResponseStatus = urlResponseStatus;
	}



}
