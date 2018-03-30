package com.dao.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.common.CommonConstants;
import com.connection.impl.QueryExecutorImpl;
import com.connection.impl.QuerySpecifierWrapperList;
import com.dao.SimpleInterfaceDao;
import com.dao.CVAuditStaticUserClass;

public class CVAuditStaticUserImpl <T> implements SimpleInterfaceDao <T>{

	@Autowired
	DataSource cvLN1dataSource;
	
	@Autowired
	DataSource cvLN3dataSource;
	

	/***@Autowired
	CVAuditStaticUserParams CVAuditStaicNamesBean;
	***/
	
	
	private List <String> names;
	
	public void printNamesList(){
		
		log.info("Size of names:" + names.size());
		
		Iterator Iter = names.iterator();
		
		while (Iter.hasNext()){
		
			String namesvalue = Iter.next().toString();
			log.info("printing element:" + namesvalue);
		}
	}
	
	public void setNamesList (List <String> names){
		this.names = names;
	}
	
	static Logger log = Logger.getLogger(CVAuditStaticUserImpl.class.getName());

	//this is dummy method, nowhere going to use here
	public void setDataSource(DataSource datasource){
		
		log.info("setting the datasource");
	}
	
	public List<T> loadAll(String query){
		List<CVAuditStaticUserClass> listinfo  =  null;
		
		return (List <T>) listinfo;
	}
	
	public List<String> loadColumnNames(String query){
		List<String> columnNames = null;

		return columnNames;
	}
	
	/***
	public List<T> loadAll(String query,String datasourceName){
		
		List<CVAuditStaticUserClass> listinfo  =  null;

		log.info("Executing query loadAll:" + query);

		//printing the values in the name list
		printNamesList();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource(datasourceName)); 
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//parameters.addValue("names", CVAuditStaicNamesBean.getNames());
		//parameters.addValue("names", names);
		parameters.addValue("names", "'YP2MD'");
		

		RowMapper<CVAuditStaticUserClass> rowMapper = new BeanPropertyRowMapper<CVAuditStaticUserClass>(CVAuditStaticUserClass.class);
		
		listinfo  = jdbcTemplate.query(query,rowMapper,parameters);
		
		
		return (List <T>) listinfo;
	}
	***/

	public List<T> loadAll(String query,String datasourceName){
		
		List<CVAuditStaticUserClass> listinfo  =  null;

		log.info("Executing query loadAll:" + query);

		//printing the values in the name list
		printNamesList();

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(datasourceName));
		
	    
	    
		RowMapper<CVAuditStaticUserClass> rowMapper = new BeanPropertyRowMapper<CVAuditStaticUserClass>(CVAuditStaticUserClass.class);
		listinfo =   namedParameterJdbcTemplate.query(query, Collections.singletonMap("names", names),rowMapper);

		return (List <T>) listinfo;
	}

	
	public List<String> loadColumnNames(String query,String datasourceName){
		List<String> columnNames = null;

		log.info("Executing query loadColumnNames:" + query);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource(datasourceName)); 
		columnNames  = jdbcTemplate.queryForList(query,String.class);

		return columnNames;
	}
	

	private DataSource getDataSource(String datasourceName){

		//set the datasource to be used for query execution.
		if (datasourceName.compareToIgnoreCase(CommonConstants.cvLN1Instance)==0){
			log.info(" setting the datasource:" + CommonConstants.cvLN1Instance);
			return cvLN1dataSource;					
		}
		else if(datasourceName.compareToIgnoreCase(CommonConstants.cvLN1Instance)==0){
			log.info(" setting the datasource:" + CommonConstants.cvLN3Instance);
			return cvLN3dataSource;
		}

		log.info(" setting the null datasource, should not be reaching here");
		return null;
	}
	
	
}
