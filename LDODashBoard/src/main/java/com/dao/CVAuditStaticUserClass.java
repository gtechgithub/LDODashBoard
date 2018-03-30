package com.dao;

public class CVAuditStaticUserClass {

	private String db_stamp_string;
	private Integer db_seq;
	
	private String operator;
	private String action;
	private Integer cv_table;
	private Integer id;
	private String comments;
	
	
	public String getDb_stamp_string() {
		return db_stamp_string;
	}
	public void setDb_stamp_string(String db_stamp_string) {
		this.db_stamp_string = db_stamp_string;
	}
	public Integer getDb_seq() {
		return db_seq;
	}
	public void setDb_seq(Integer db_seq) {
		this.db_seq = db_seq;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getCv_table() {
		return cv_table;
	}
	public void setCv_table(Integer cv_table) {
		this.cv_table = cv_table;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
