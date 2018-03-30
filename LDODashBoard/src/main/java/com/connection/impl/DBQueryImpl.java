package com.connection.impl;

import java.util.Iterator;
import java.util.List;

import com.connection.DBQuery;
import org.apache.log4j.Logger;

public class DBQueryImpl implements DBQuery {
	
	public List <String> DBName;
	public List <String> UrlName;
	public List <String> QueryString;

	static Logger log = Logger.getLogger(DBQueryImpl.class.getName());
	
	public void setDBName(List<String> dbName)
	{
		log.info("inside setDBName method");
		this.DBName = dbName;
	}
	
	public List<String> getDBName(){
		log.info("inside getDBName method");
		return this.DBName;
	}
	
	public void setUrlName(List<String> UrlName){
		log.info("inside setUrlName method");
		this.UrlName = UrlName;
	}
	
	public List<String> getUrlName(){
		log.info("inside getUrlName method");
		return this.UrlName;
	}
	
	public void setQueryString(List<String> QueryString){
		log.info("inside setQueryString method");
		this.QueryString = QueryString;
	}
	
	public List<String> getQueryString(){
		log.info("inside getQueryString method");
		return this.QueryString;
	}
	
	public String findQueryString(String urlName,String dbName){
		
		log.info("inside findQueryString method");
		Iterator <String> iteratorQuery = QueryString.iterator();
		Iterator <String> iteratorUrl = UrlName.iterator();
		Iterator <String> iteratorDbName = DBName.iterator();
		
		String query,url, dbname;
		
		//iterate the loop to get the required query
		while (iteratorQuery.hasNext() && iteratorDbName.hasNext() && iteratorUrl.hasNext()) {
			
			dbname= iteratorDbName.next();
			url =  iteratorUrl.next();
			query= iteratorQuery.next();
			log.info("iterating for DBName:" + dbname + " and Url:"+ url);
			
			if ( (dbname.compareToIgnoreCase(dbName) == 0) && 
				 (url.compareToIgnoreCase(urlName) == 0))
			{
				log.info("match found for Url:" + urlName + " and Dbname:" +dbName );
				return query;
			}
		}
		
		//query does not match, this is really trouble
		log.info("No match found for Url:" + urlName + " and Dbname:" + dbName);
		return "";
	}
}
