package com.connection.impl;

import java.util.List;

public class QuerySpecifierWrapper {

	//this is query which will retrieve the results
	private String actualQuery;
	
	//this is the query which extracts the column names
	private String columnQuery;

	//this is the query Id name identifier
	private String queryIDNameIdentifer;
	
	/* query group identifier
	 * which will be used to identify the group of queries
	 * 
	 */
	
	private String QueryGroupNameIdentifer;

	
	public String getActualQuery() {
		return actualQuery;
	}

	public void setActualQuery(String actualQuery) {
		this.actualQuery = actualQuery;
	}

	public String getColumnQuery() {
		return columnQuery;
	}

	public void setColumnQuery(String columnQuery) {
		this.columnQuery = columnQuery;
	}

	public String getQueryIDNameIdentifer() {
		return queryIDNameIdentifer;
	}

	public void setQueryIDNameIdentifer(String queryIDNameIdentifer) {
		this.queryIDNameIdentifer = queryIDNameIdentifer;
	}

	public String getQueryGroupNameIdentifer() {
		return QueryGroupNameIdentifer;
	}

	public void setQueryGroupNameIdentifer(String queryGroupNameIdentifer) {
		QueryGroupNameIdentifer = queryGroupNameIdentifer;
	}


}
