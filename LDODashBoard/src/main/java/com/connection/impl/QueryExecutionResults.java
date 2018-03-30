package com.connection.impl;

import java.util.List;

import com.dao.CVAuditStaticUserClass;
import com.dao.ClusterInfo;
import com.dao.Progs;
import com.dao.SysControl;
import com.dao.CVAuditStaticUserClass;
import com.dao.CVModifClass;

public class QueryExecutionResults {

	private String queryDescription;
	
	private List<String> queryColumnNames;
	
	private List <ClusterInfo> queryRowColResult;
	
	private List <Progs> progsRowColResult;
	
	private List <SysControl> sysControlRowColResult;

	private List <CVAuditStaticUserClass> cvAuditStaticRowColRslt;
	private List <CVModifClass> cvModifRowColRslt;
	
	public String getQueryDescription() {
		return queryDescription;
	}

	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}

	public List<String> getQueryColumnNames() {
		return queryColumnNames;
	}

	public void setQueryColumnNames(List<String> queryColumnNames) {
		this.queryColumnNames = queryColumnNames;
	}

	public List<ClusterInfo> getQueryRowColResult() {
		return queryRowColResult;
	}

	public void setQueryRowColResult(List<ClusterInfo> queryRowColResult) {
		this.queryRowColResult = queryRowColResult;
	}

	public List<Progs> getProgsRowColResult() {
		return progsRowColResult;
	}

	public void setProgsRowColResult(List<Progs> progsRowColResult) {
		this.progsRowColResult = progsRowColResult;
	}
	
	public List<SysControl> getSysControlRowColResult() {
		return sysControlRowColResult;
	}

	public void setSysControlRowColResult(List<SysControl> sysControlRowColResult) {
		this.sysControlRowColResult = sysControlRowColResult;
	}
	
	public List<CVAuditStaticUserClass> getCvAuditStaticRowColRslt() {
		return cvAuditStaticRowColRslt;
	}

	public void setCvAuditStaticRowColRslt(List<CVAuditStaticUserClass> cvAuditStaticRowColRslt) {
		this.cvAuditStaticRowColRslt = cvAuditStaticRowColRslt;
	}

	public List<CVModifClass> getCvModifRowColRslt() {
		return cvModifRowColRslt;
	}

	public void setCvModifRowColRslt(List<CVModifClass> cvModifRowColRslt) {
		this.cvModifRowColRslt = cvModifRowColRslt;
	}
}
