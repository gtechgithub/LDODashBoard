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
import com.dao.Progs;
import com.dao.SimpleInterfaceDao;

public class ProgsDaoImpl <T> implements SimpleInterfaceDao <T> {

	public DataSource getGlobalcashmanmdataSource() {
		return GlobalcashmanmdataSource;
	}


	public void setGlobalcashmanmdataSource(DataSource datasource) {
		this.GlobalcashmanmdataSource = datasource;
	}

	private DataSource GlobalcashmanmdataSource;
	
	static Logger log = Logger.getLogger(ProgsDaoImpl.class.getName());
	
	public void setDataSource(DataSource datasource){
		log.info("setting the datasource GlobalcashmanmdataSource");
		this.GlobalcashmanmdataSource = datasource;
	}
	
	public List<T> loadAll(String query){
		List<Progs> listinfo  =  null;

		log.info("Executing query loadAll:" + query);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(GlobalcashmanmdataSource); 

		listinfo  = jdbcTemplate.query(query,new BeanPropertyRowMapper(Progs.class));
		return (List <T>) listinfo;
	}

	public List<String> loadColumnNames(String query){
		List<String> columnNames = null;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(GlobalcashmanmdataSource); 
		columnNames  = jdbcTemplate.queryForList(query,String.class);

		return columnNames;
	}
	
}
