package com.dao;

import java.util.List;
import javax.sql.DataSource;


import com.connection.impl.QuerySpecifierWrapperList;

 public interface SimpleInterfaceDao<T>  {
	public List<String> loadColumnNames(String query);
	public List<T> loadAll(String query);	
	
	public void setDataSource(DataSource datasource);
	
}
