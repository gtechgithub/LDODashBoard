package com.connection.impl;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.common.CommonConstants;
import com.dao.ClusterInfo;
import com.dao.Progs;
import com.dao.SimpleInterfaceDao;
import com.dao.SysControl;
import com.dao.impl.CVAuditStaticUserImpl;
import com.dao.impl.CVAuditStaticUserParams;
import com.dao.impl.ClusterInfoDaoImpl;
import com.dao.impl.ProgsDaoImpl;
import com.dao.impl.SysControlDaoImpl;
import com.dao.CVAuditStaticUserClass;
import com.dao.CVModifClass;
import com.connection.impl.QuerySpecifierWrapperList;

public class QueryExecutorImpl {


	@Autowired
	QuerySpecifierWrapperList querySpecifierWrapperListBean;
	
	@Autowired
	DataSource GlobalcashmanmdataSource;
	
	@Autowired
	ProgsDaoImpl ProgsDaoImplBean;
	
	@Autowired
	SysControlDaoImpl SysControlDaoImplBean;
	
	@Autowired
	CVAuditStaticUserImpl CVAuditStaticUserImplBean;
	
	//@Autowired
	//CVAuditStaticUserParams CVAuditStaicNamesBean;
	
	static Logger log = Logger.getLogger(QueryExecutorImpl.class.getName());

	public QueryExecutionResults executeQueryWrapperByQueryID(String queryID){
		
		QueryExecutionResults queryResults = null;
		List <String> queryColumnNames = null;
		//List <Object> queryRowColResult = null;
		QuerySpecifierWrapper querySpecifier =  null;
		
		if (! queryID.isEmpty()) {

			if (queryID.equals(CommonConstants.GCMTLMClusterInfo)){

				querySpecifier = extractQuerySpecifierFromList(queryID);


				SimpleInterfaceDao <ClusterInfo> daoInterface =  new  ClusterInfoDaoImpl<ClusterInfo>();
				daoInterface.setDataSource(GlobalcashmanmdataSource);
				
				queryColumnNames = daoInterface.loadColumnNames(querySpecifier.getColumnQuery());
				List <ClusterInfo> ClusterInfoResult  =  daoInterface.loadAll(querySpecifier.getActualQuery());
				
				for (int size = 0; size < ClusterInfoResult.size(); size++){
					log.info(" ClusterInfoResult product:" + ClusterInfoResult.get(size).getProduct());
					log.info(" ClusterInfoResult ipadrress:" + ClusterInfoResult.get(size).getIp_address());
				}
				
				queryResults = new QueryExecutionResults();						
				queryResults.setQueryDescription(CommonConstants.GCMTLMClusterDesc);
				queryResults.setQueryColumnNames(queryColumnNames);
				queryResults.setQueryRowColResult(ClusterInfoResult);
			}
			else if (queryID.equals(CommonConstants.GCMTLMMactchProgs)){
				querySpecifier = extractQuerySpecifierFromList(queryID);
				
				
				//ClusterInfoDaoImplBean.setDataSource(GlobalcashmanmdataSource);
				log.info(" inside the executeQueryWrapperByQueryID for GCMTLMMactchProgs");
				queryColumnNames = ProgsDaoImplBean.loadColumnNames(querySpecifier.getColumnQuery());
				List <Progs> progsResult =  ProgsDaoImplBean.loadAll(querySpecifier.getActualQuery());

				queryResults = new QueryExecutionResults();						
				queryResults.setQueryDescription(CommonConstants.GCMTLMProgsDesc);
				queryResults.setQueryColumnNames(queryColumnNames);
				queryResults.setProgsRowColResult(progsResult);
			}
			else if (queryID.equals(CommonConstants.GCMTLMSysControl)){
				querySpecifier = extractQuerySpecifierFromList(queryID);
				
				//ClusterInfoDaoImplBean.setDataSource(GlobalcashmanmdataSource);
				log.info(" inside the executeQueryWrapperByQueryID for GCMTLMSysControl");
				queryColumnNames = SysControlDaoImplBean.loadColumnNames(querySpecifier.getColumnQuery());
				List <SysControl> SysControlResult =  SysControlDaoImplBean.loadAll(querySpecifier.getActualQuery());

				queryResults = new QueryExecutionResults();						
				queryResults.setQueryDescription(CommonConstants.GCMTLMSysCtrlDesc);
				queryResults.setQueryColumnNames(queryColumnNames);
				queryResults.setSysControlRowColResult(SysControlResult);
			}
		}
		
		return queryResults;
				
	}

	public <T> QueryExecutionResults executeQueryWrapperByQueryID(String queryID,String datasourceName, T classname){
		
		QueryExecutionResults queryResults = null;
		List <String> queryColumnNames = null;
		//List <Object> queryRowColResult = null;
		QuerySpecifierWrapper querySpecifier =  null;
		
		if (! queryID.isEmpty() && !datasourceName.isEmpty()) {

			if (queryID.equals(CommonConstants.CVAuditStaticUser)){

				log.info(" inside the executeQueryWrapperByQueryID for CVAuditStaticUser");
				
				//extract the query based on the query and pass to the corresponding DAO for execution
				querySpecifier = extractQuerySpecifierFromList(queryID);
				
				
				//SimpleInterfaceDao <CVAuditStaticUserClass> daoInterface =  new  CVAuditStaticUserImpl<CVAuditStaticUserClass>();
				
				//execute the loadcolumnnames and loadall to get the values
				//CVAuditStaticUserImplBean.setNamesList(names);
				List <String> names = ((CVAuditStaticUserParams) classname).getNames();
				
				CVAuditStaticUserImplBean.setNamesList(names);
				queryColumnNames = CVAuditStaticUserImplBean.loadColumnNames(querySpecifier.getColumnQuery(),datasourceName);
				List <CVAuditStaticUserClass> cvauditstaticResult =  CVAuditStaticUserImplBean.loadAll(querySpecifier.getActualQuery(),datasourceName);
				
				queryResults = new QueryExecutionResults();						
				queryResults.setQueryDescription(CommonConstants.CVAuditStaticUserDesc);
				queryResults.setQueryColumnNames(queryColumnNames);
				queryResults.setCvAuditStaticRowColRslt(cvauditstaticResult);
			}
		}
		
		return queryResults;
				
	}
	
	private  QuerySpecifierWrapper extractQuerySpecifierFromList(String queryID){
		QuerySpecifierWrapper queryspecifier = null;
		
		List <QuerySpecifierWrapper> queryspecifierList = querySpecifierWrapperListBean.getQuerySpecifierWrapperList();
		
		
		
		for (int size =0 ; size < queryspecifierList.size() ; size++){
			if (queryID.equals(queryspecifierList.get(size).getQueryIDNameIdentifer())){
				queryspecifier = queryspecifierList.get(size);
				break;
			}		
		}

		/****
		Iterator iter = queryspecifierList.iterator();

		while (iter.hasNext()) {
			QuerySpecifierWrapper queryWrapper  = (QuerySpecifierWrapper) iter;
			if (queryID.equals(queryWrapper.getQueryIDNameIdentifer())){
				queryspecifier = (QuerySpecifierWrapper) iter;
				break;
			}
		}
		****/
		
		return queryspecifier;
	}
	
	//TODO be removed OR commented since dependency injection is already in place
	/****
	public QuerySpecifierWrapperList getQuerySpecifierWrapperListBean() {
		return querySpecifierWrapperListBean;
	}

	public void setQuerySpecifierWrapperListBean(QuerySpecifierWrapperList querySpecifierWrapperListBean) {
		this.querySpecifierWrapperListBean = querySpecifierWrapperListBean;
	}


	
		//TODO to be removed or commented once tested
		public QueryExecutionResults executeQueryWrapperByQueryID(QuerySpecifierWrapperList querySpecifierWrapperListBean,String queryID){
		
		QueryExecutionResults queryResults = null;
		List <String> queryColumnNames = null;
		List <Object> queryRowColResult = null;
		QuerySpecifierWrapper querySpecifier =  null;
		
		setQuerySpecifierWrapperListBean(querySpecifierWrapperListBean);
		
		if (! queryID.isEmpty()) {

			if (queryID.equals(CommonConstants.GCMTLMClusterInfo)){

				querySpecifier = extractQuerySpecifierFromList(queryID);


				SimpleInterfaceDao <ClusterInfo> daoInterface =  new  ClusterInfoDaoImpl<ClusterInfo>();
				
				queryColumnNames = daoInterface.loadColumnNames(querySpecifier.getColumnQuery());
				queryRowColResult =    (List) daoInterface.loadAll(querySpecifier.getActualQuery());
				
				queryResults = new QueryExecutionResults();						
				queryResults.setQueryDescription(CommonConstants.GCMTLMClusterDesc);
				queryResults.setQueryColumnNames(queryColumnNames);
				queryResults.setQueryRowColResult(queryRowColResult);
			}
		}
		
		return queryResults;
				
	}
	
	
	****/

}
