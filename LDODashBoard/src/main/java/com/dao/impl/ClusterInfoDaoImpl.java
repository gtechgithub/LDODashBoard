package com.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.common.CommonConstants;
import com.connection.impl.QueryExecutorImpl;
import com.connection.impl.QuerySpecifierWrapperList;
import com.dao.ClusterInfo;
import com.dao.SimpleInterfaceDao;

public class ClusterInfoDaoImpl <T> implements SimpleInterfaceDao <T> {

	//@Autowired
	private DataSource GlobalcashmanmdataSource;
	
	static Logger log = Logger.getLogger(ClusterInfoDaoImpl.class.getName());
	
	public void setDataSource(DataSource datasource){
		log.info("setting the datasource GlobalcashmanmdataSource");
		this.GlobalcashmanmdataSource = datasource;
	}

	
	public List<T> loadAll(String query){
		List<ClusterInfo> listClusterinfo  =  null;

		log.info("Executing query loadAll:" + query);

		//JdbcTemplate jdbcTemplate = (JdbcTemplate) GlobalcashmanmdataSource;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(GlobalcashmanmdataSource); 

		listClusterinfo  = jdbcTemplate.query(query,new BeanPropertyRowMapper(ClusterInfo.class));
		
		for (int size= 0 ; size < listClusterinfo.size(); size++ ){
			log.info(" listClusterinfo product:" +  listClusterinfo.get(size).getProduct());
			log.info(" listClusterinfo ipaddress:" + listClusterinfo.get(size).getIp_address());
		}
		
		return (List <T>) listClusterinfo;
	}

	public List<String> loadColumnNames(String query){
		List<String> columnNames = null;

		//TODO to be removed or commented once the testing is done.
		//callContextToSetDataSource();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(GlobalcashmanmdataSource); 
		columnNames  = jdbcTemplate.queryForList(query,String.class);

		return columnNames;
	}
	
	public void insert(ClusterInfo customer){
		
	}
	
	//TODO to be removed or commented once the testing done,since dependency injection is already in place.
	/*******
	private DataSource GlobalcashmanmdataSource; 
	
	//TODO to be removed after the testing, since it is an auto wired
	public DataSource getGlobalcashmanmdataSource() {
		return GlobalcashmanmdataSource;
	}

	public void setGlobalcashmanmdataSource(DataSource globalcashmanmdataSource) {
		GlobalcashmanmdataSource = globalcashmanmdataSource;
	}

	public void callContextToSetDataSource(){
		ApplicationContext context = new FileSystemXmlApplicationContext("M:\\mvc-database.xml");
		
		log.info ("before get GlobalcashmanmdataSource");
		
		DataSource dataSource = (DataSource)context.getBean("GlobalcashmanmdataSource");
		
		
		setGlobalcashmanmdataSource(dataSource);
	}

	****/
}
