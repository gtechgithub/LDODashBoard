package com.connection;

import java.util.List;

public interface DBQuery {

	public void setDBName(List<String> dbName);
	public List<String> getDBName();
	
	public void setUrlName(List<String> UrlName);
	public List<String> getUrlName();
	
	public void setQueryString(List<String> QueryString);
	public List<String> getQueryString();
	
	public String findQueryString(String urlName,String dbName);
	
}
