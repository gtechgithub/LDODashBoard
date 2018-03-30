package com.connection.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.common.CommonConstants;
import com.dao.ClusterInfo;
import com.implementation.LnkConnectionWrapper;
import com.implementation.TestLnkConnection;


public class TestQueryExecutorImpl {

	
	static Logger log = Logger.getLogger(TestQueryExecutorImpl.class.getName());
	
	public void testQueryExecutor(){
		
		log.info ("before xml resource");

		ApplicationContext context = new FileSystemXmlApplicationContext("M:\\mvc-database.xml");
		log.info ("before get bean");
		QuerySpecifierWrapperList wrapperList = (QuerySpecifierWrapperList)context.getBean("querySpecifierWrapperListBean");
		
		QueryExecutorImpl execImpl = new QueryExecutorImpl();
		QueryExecutionResults execResults = null;

		log.info ("before calling the set specifier wrapper list bean");

		
		//TODO to be uncommented for testing
		/*****
		//set the specifier wrapper list 
		execImpl.setQuerySpecifierWrapperListBean(wrapperList);
		
		log.info ("before calling the execution query wrapper");
		QueryExecutionResults execResults = execImpl.executeQueryWrapperByQueryID(wrapperList, CommonConstants.GCMTLMClusterInfo);
		****/
		
		log.info("" + execResults.getQueryDescription());
		
		
		List <String> columnNames = execResults.getQueryColumnNames();
		
		/***
		Iterator iter = columnNames.iterator();

		while (iter.hasNext()){
			log.info(iter);
		}
		***/
		
		for (int size= 0; size <  columnNames.size(); size++){
			
			log.info(columnNames.get(size));
		}
		
		log.info (" printing the actual values");
		
		List <ClusterInfo> clusterinfoList = (List) execResults.getQueryRowColResult();
		
		/***
		Iterator clusterinfoIterator = clusterinfoList.iterator();
		
		while (clusterinfoIterator.hasNext()){
			ClusterInfo clusterinfo = (ClusterInfo) clusterinfoIterator;
			log.info (" getProduct:" + clusterinfo.getProduct());
			log.info (" getIp_address:" + clusterinfo.getIp_address());
		}
		***/
		
		for (int size= 0; size <  clusterinfoList.size(); size++){
			
			log.info (" getProduct:" + clusterinfoList.get(size).getProduct());
			log.info (" getIp_address:" + clusterinfoList.get(size).getIp_address());
			log.info (" getHttp_port:" + clusterinfoList.get(size).getHttp_port());
			log.info (" getRmi_port:" + clusterinfoList.get(size).getRmi_port());
			log.info (" getNode_type:" + clusterinfoList.get(size).getNode_type());
			log.info (" getHost_name:" + clusterinfoList.get(size).getHost_name());
			log.info (" getStatus:" + clusterinfoList.get(size).getStatus());
			log.info (" getTimestamp:" + clusterinfoList.get(size).getLaastseen());
		}

	}
	
	public static void main (String args[]){
		
		TestQueryExecutorImpl testExec = new TestQueryExecutorImpl();
		testExec.testQueryExecutor();
	}

}
