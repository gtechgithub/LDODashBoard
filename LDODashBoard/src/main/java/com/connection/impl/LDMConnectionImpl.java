package com.connection.impl;

import com.connection.DBConnection;
import com.common.CommonConstants;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;

public class LDMConnectionImpl implements DBConnection {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private DBQueryImpl dbQuery;
	
	static Logger log = Logger.getLogger(LDMConnectionImpl.class.getName());
	
	public void setdataSource(DataSource dataSource)
	{
		log.info("inside setDataSource method");
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	public void setdbQuery(DBQueryImpl dbQuery)
	{
		log.info("inside setdbQuery method");
		this.dbQuery = dbQuery;
	}
	
	public int checkUnProcessed()
	{
		int value = -1;
		try 
		{
			log.info("inside checkUnProcessed method");
			
			
			log.info(" calling  findQueryString method");
			String queryString  = dbQuery.findQueryString(CommonConstants.LdmUnprocessedLink, 
					CommonConstants.LDMDbName);
			
			log.info(" QuerySring returned from  findQueryString method: \n " + queryString);
			value = jdbcTemplate.queryForInt(queryString);
			
			log.info("value:" + value +  "returned after executing query");
			return value;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return value;
		
	}
}
