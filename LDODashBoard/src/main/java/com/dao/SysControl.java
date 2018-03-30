package com.dao;

public class SysControl {

	private String region_name;
	private String current_busi_date;
	private String ui_current_busi_date;
	private int active_status;
	
	public int getActive_status() {
		return active_status;
	}
	public void setActive_status(int active_status) {
		this.active_status = active_status;
	}
	public String getCurrent_busi_date() {
		return current_busi_date;
	}
	public void setCurrent_busi_date(String current_busi_date) {
		this.current_busi_date = current_busi_date;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getUi_current_busi_date() {
		return ui_current_busi_date;
	}
	public void setUi_current_busi_date(String ui_current_busi_date) {
		this.ui_current_busi_date = ui_current_busi_date;
	}

	
}
