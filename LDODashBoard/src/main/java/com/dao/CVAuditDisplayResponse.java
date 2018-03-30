package com.dao;

import java.util.List;

public class CVAuditDisplayResponse {

	private List <CVAuditStaticUserClass> cvAuditClassList;
	private List <CVModifClass> cvModifList;

	public List<CVAuditStaticUserClass> getCvAuditClassList() {
		return cvAuditClassList;
	}
	public void setCvAuditClassList(List<CVAuditStaticUserClass> cvAuditClassList) {
		this.cvAuditClassList = cvAuditClassList;
	}
	public List<CVModifClass> getCvModifList() {
		return cvModifList;
	}
	public void setCvModifList(List<CVModifClass> cvModifList) {
		this.cvModifList = cvModifList;
	}
}
