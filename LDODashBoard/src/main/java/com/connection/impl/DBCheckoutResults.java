package com.connection.impl;

public class DBCheckoutResults {
	private String description;
	private String dbName;
	private boolean activityStatus;
	private StringBuffer Querylogs;
	
	public StringBuffer getQuerylogs() {
		return Querylogs;
	}

	public void setQuerylogs(StringBuffer querylogs) {
		Querylogs = querylogs;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public boolean getActivityStatus() {
		return activityStatus;
	}
	
	public void setActivityStatus(boolean activityStatus) {
		this.activityStatus = activityStatus;
	}
}
