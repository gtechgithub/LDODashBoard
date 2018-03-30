package com.connection.impl;

import com.common.CommonConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.apache.log4j.Logger;
import com.connection.impl.DBCheckoutResults;

public class DBCheckoutImpl {
	
	
	private DataSource CVTSEdataSource;
	private DataSource CVHKdataSource;
	private DataSource CVSGXdataSource;
	private DataSource CVTOCOMdataSource;
	private DataSource CVLN1dataSource;
	private DataSource CVLN3dataSource;

	@Autowired 
	DataSource ldmdataSource;

	@Autowired 
	DataSource OpusdataSource;

	@Autowired 
	DataSource CdrdataSource;

	@Autowired
	DataSource tlmdataSource;

	@Autowired
	DataSource tlmETLdataSource;
	
	@Autowired
	DataSource GlobalcashmanmdataSource;
	
	@Autowired
	DataSource MDSWebProddataSource;

	@Autowired
	DataSource MDSWebDRdataSource;
	
	@Autowired
	DataSource MDImatchProddataSource;
	
	@Autowired
	DataSource MDImatchDRdataSource;
	
	@Autowired
	DataSource MDProtProddataSource;
	
	@Autowired
	DataSource MDProtDRdataSource;
	
	private DBQueryImpl dbQuery;
	
	static Logger log = Logger.getLogger(DBCheckoutImpl.class.getName());
	
	public void setCVTSEdataSource(DataSource dataSource)
	{
		log.info("inside setCVTSEdataSource method");
		this.CVTSEdataSource = dataSource;
	}

	public void setCVHKdataSource(DataSource dataSource)
	{
		log.info("inside setCVHKdataSource method");
		this.CVHKdataSource = dataSource;
	}

	public void setCVSGXdataSource(DataSource dataSource)
	{
		log.info("inside setCVSGXdataSource method");
		this.CVSGXdataSource = dataSource;
	}

	public void setCVTOCOMdataSource(DataSource dataSource)
	{
		log.info("inside CVTOCOMdataSource method");
		this.CVTOCOMdataSource = dataSource;
	}

	public void setCVLN1dataSource(DataSource dataSource)
	{
		log.info("inside CVLN1dataSource method");
		this.CVLN1dataSource = dataSource;
	}
	
	public void setCVLN3dataSource(DataSource dataSource)
	{
		log.info("inside CVLN3dataSource method");
		this.CVLN3dataSource = dataSource;
	}
	public void setdbQuery(DBQueryImpl dbQuery)
	{
		log.info("inside setdbQuery method");
		this.dbQuery = dbQuery;
	}
	
	public List <DBCheckoutResults> getCheckoutResults(String dbName )
	{
		DBCheckoutResults dbCheckoutResults = new DBCheckoutResults();
		List <DBCheckoutResults> dbCheckoutResultsList = new ArrayList<DBCheckoutResults>();
		
		if (dbName.equals(CommonConstants.LDMDbName)){
			dbCheckoutResults = processDBCheckout(ldmdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.LDMDbName,
					                                 CommonConstants.LDMDBDesc,
					                                 CommonConstants.LDMDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);
		}
		else if (dbName.equals(CommonConstants.OPUSdbName)){
			dbCheckoutResults = processDBCheckout(OpusdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.OPUSdbName,
					                                 CommonConstants.OPUSBDesc,
					                                 CommonConstants.OPUSDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);
		}
		else if (dbName.equals(CommonConstants.CDRdbName)){
			dbCheckoutResults = processDBCheckout(CdrdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.CDRdbName,
					                                 CommonConstants.CDRDBDesc,
					                                 CommonConstants.CDRDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);
		}
		else if (dbName.equals(CommonConstants.CVCheckoutEmeaDbName)){
			//LONDON 1 
			dbCheckoutResults = processDBCheckout(CVLN1dataSource,
					                                 CommonConstants.DatabaseCheckoutEMEA,
					                                 CommonConstants.CVCheckoutEmeaDbName,
					                                 CommonConstants.cvLN1DBDesc,
					                                 CommonConstants.cvLN1Instance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			
			//LONDON 3 
			dbCheckoutResults = processDBCheckout(CVLN3dataSource,
					                                 CommonConstants.DatabaseCheckoutEMEA,
					                                 CommonConstants.CVCheckoutEmeaDbName,
					                                 CommonConstants.cvLN3DBDesc,
					                                 CommonConstants.cvLN3Instance);
			dbCheckoutResultsList.add(dbCheckoutResults);
		}
		else if (dbName.equals(CommonConstants.CVCheckoutAmericaDbName)){
			//TODO need to add the code here for the CV america.
		}
		else if (dbName.equals(CommonConstants.TLMdbName)){
			//LONDON 1 
			dbCheckoutResults = processDBCheckout(tlmdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.TLMdbName,
					                                 CommonConstants.TLMDBDesc,
					                                 CommonConstants.TLMDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			
			//LONDON 3 
			dbCheckoutResults = processDBCheckout(tlmETLdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.TLMdbName,
					                                 CommonConstants.TLMETLDBDesc,
					                                 CommonConstants.TLMETLDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);
		}
		else if (dbName.equals(CommonConstants.GlobalCashTLMdbName) ) {
	
			//Global cash man results 
			dbCheckoutResults = processDBCheckout(GlobalcashmanmdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.GlobalCashTLMdbName,
					                                 CommonConstants.GCMTLMETLDBDesc,
					                                 CommonConstants.GCMTLMLDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);
			
		}
		else if (dbName.equals(CommonConstants.MatchDerivativeDBCheckout) ) {
			
			//MD web PROD results 
			dbCheckoutResults = processDBCheckout(MDSWebProddataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchWebDerivativedbName,
					                                 CommonConstants.MDWebProdDBDesc,
					                                 CommonConstants.MDWebPRODDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			//MD web DR results 
			dbCheckoutResults = processDBCheckout(MDSWebDRdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchWebDerivativedbName,
					                                 CommonConstants.MDWebDRDBDesc,
					                                 CommonConstants.MDWebDRDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			//MD Imatch PROD results 
			dbCheckoutResults = processDBCheckout(MDImatchProddataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchImatchDerivativedbName,
					                                 CommonConstants.MDImatchProdDBDesc,
					                                 CommonConstants.MDImatchPRODDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			//MD Imatch DR results 
			dbCheckoutResults = processDBCheckout(MDImatchDRdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchImatchDerivativedbName,
					                                 CommonConstants.MDImatchDRDBDesc,
					                                 CommonConstants.MDImatchDRDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			//MD Protector PROD results 
			dbCheckoutResults = processDBCheckout(MDProtProddataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchProtDerivativedbName,
					                                 CommonConstants.MDProtProdDBDesc,
					                                 CommonConstants.MDProtPRODDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

			//MD Protector DR results 
			dbCheckoutResults = processDBCheckout(MDProtDRdataSource,
					                                 CommonConstants.DatabaseCheckout,
					                                 CommonConstants.MatchProtDerivativedbName,
					                                 CommonConstants.MDProtDRDBDesc,
					                                 CommonConstants.MDProtDRDBInstance);
			dbCheckoutResultsList.add(dbCheckoutResults);

		}

		return dbCheckoutResultsList;
	}
	public List <DBCheckoutResults> getCheckoutResults()
	{
		DBCheckoutResults dbCheckoutResults = new DBCheckoutResults();
		List <DBCheckoutResults> dbCheckoutResultsList = new ArrayList<DBCheckoutResults>();
		
		//TSE 
		dbCheckoutResults = processDBCheckout(CVTSEdataSource,
				                                 CommonConstants.DatabaseCheckout,
				                                 CommonConstants.CVCheckoutDbName,
				                                 CommonConstants.cvTSEDBDesc,
				                                 CommonConstants.cvTSEInstance);
		dbCheckoutResultsList.add(dbCheckoutResults);

		//HKFE 
		dbCheckoutResults = processDBCheckout(CVHKdataSource,
				                                 CommonConstants.DatabaseCheckout,
				                                 CommonConstants.CVCheckoutDbName,
				                                 CommonConstants.cvHKFEDBDesc,
				                                 CommonConstants.cvHKFEInstance);
		dbCheckoutResultsList.add(dbCheckoutResults);

		//SGX 
		dbCheckoutResults = processDBCheckout(CVSGXdataSource,
				                                 CommonConstants.DatabaseCheckout,
				                                 CommonConstants.CVCheckoutDbName,
				                                 CommonConstants.cvSGXDBDesc,
				                                 CommonConstants.cvSGXInstance);
		dbCheckoutResultsList.add(dbCheckoutResults);

		//TOCOM 
		dbCheckoutResults = processDBCheckout(CVTOCOMdataSource,
				                                 CommonConstants.DatabaseCheckout,
				                                 CommonConstants.CVCheckoutDbName,
				                                 CommonConstants.cvTOCOMDBDesc,
				                                 CommonConstants.cvTOCOMInstance);
		dbCheckoutResultsList.add(dbCheckoutResults);


		return dbCheckoutResultsList;
	}
	
	public DBCheckoutResults processDBCheckout(DataSource dataSource,
			                                   String link, 
			                                   String dbName,
			                                   String dbDesc,
			                                   String dbInstanceName			                                   )
	{
		DBCheckoutResults dbCheckoutResults = new DBCheckoutResults();
		StringBuffer  stringBuffer = new StringBuffer();

		//append DBDescription and Instance in the string buffer logs
		stringBuffer.append("<br> <br> ******************************************************");
		stringBuffer.append("<br> DB Description:" + dbDesc + " Database Instance:" + dbInstanceName);
		boolean returnActivityStatus = executeDBQueryWrapper(dataSource,link,dbName,stringBuffer);
		stringBuffer.append("<br> ******************************************************");

		
		dbCheckoutResults.setDescription(dbDesc);
		dbCheckoutResults.setDbName(dbInstanceName);
		dbCheckoutResults.setActivityStatus(returnActivityStatus);
		dbCheckoutResults.setQuerylogs(stringBuffer);
		
		return dbCheckoutResults;

	}
	
	public boolean executeDBQueryWrapper(DataSource dataSource,String link, String dbName,StringBuffer stringBuffer)
	{
		boolean returnVal = false;
		JdbcTemplate jdbcTemplate;
		
		
		try 
		{
			log.info("inside executeDBQueryWrapper method");
			jdbcTemplate = new JdbcTemplate(dataSource); 
			
			log.info(" calling  findQueryString method");
			String queryStringExtract  = dbQuery.findQueryString(link,dbName);
			
			log.info(" DB:[" + dbName + "] for which Query to be executed");
			log.info(" QuerySring returned from  findQueryString method: \n " + queryStringExtract);
			
			// check here, if the query string contains the multiple queries.
			// if so we need to execute one by one.
			List<String> queryStringList = retrieveStringTokenQuery(queryStringExtract);
			
			log.info("No of Query available for Execution:[" + queryStringList.size() + "]");
			Iterator Iter = queryStringList.iterator();
			
			//set here as true.
			returnVal = true;
			//stringBuffer = new StringBuffer();

			while( Iter.hasNext())
			{
				String queryStringValue = "";
				String queryString = Iter.next().toString();
				
				//boolean returnValIter = executeDBQuery(dataSource,queryString,queryStringValue);
				String returnValIter = executeDBQuery(dataSource,queryString,queryStringValue);
				
				stringBuffer.append("<br> Query String:[" + queryString + "]");
				stringBuffer.append("<br> Excution Query Returned:[" + returnValIter + "]");
				
				//if (!returnValIter)
				if (returnValIter.isEmpty())
				{
					//incase of empty, set the return value as false
					returnVal = false;
				}
			}
			return returnVal;
		}
		catch(Exception e)
		{
			log.error("Exception Received during DBconnection:" + e.getMessage());
			e.printStackTrace();
		}
		
		return returnVal;
		
	}
	
	public List <String> retrieveStringTokenQuery(String queryInput)
	{
		List<String> queryList = null;
		
		try 
		{
			String delimeter = "}";
			StringTokenizer stringToken =  new StringTokenizer(queryInput,delimeter);
			
			queryList =  new ArrayList<String>();
			while (stringToken.hasMoreElements())
			{
				String singleQuery = stringToken.nextElement().toString();
				log.info("Displaying the singleQuery:" + singleQuery);
				
				if (!singleQuery.isEmpty())
				{
					queryList.add(singleQuery);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return queryList;
	}
	
	public List <String> retrieveStringTokenQuery(String queryInput,String delimeter)
	{
		List<String> queryList = null;
		
		try 
		{
			StringTokenizer stringToken =  new StringTokenizer(queryInput,delimeter);
			
			queryList =  new ArrayList<String>();
			while (stringToken.hasMoreElements())
			{
				String singleQuery = stringToken.nextElement().toString();
				log.info("Displaying the singleQuery:" + singleQuery);
				
				if (!singleQuery.isEmpty())
				{
					queryList.add(singleQuery);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return queryList;
	}
	
	public String executeDBQuery(DataSource dataSource,String query,String QueryResultValue){
	
		JdbcTemplate jdbcTemplate;
		//boolean returnVal = false;
		
		//reset to the empty
		QueryResultValue = "";

		try 
		{
			jdbcTemplate = new JdbcTemplate(dataSource); 
			
			QueryResultValue = (String)jdbcTemplate.queryForObject(query,String.class);
			
			if (! QueryResultValue.isEmpty())
			{
				log.info("query execution is Successful");
				//returnVal = true;
				//return returnVal;
				return QueryResultValue;
			}
			
			log.error("query execution is not Successful!!! please check logs");
			//returnVal = false;
			//return returnVal;
			return QueryResultValue;
		}
		catch(Exception e)
		{
			log.error("Exception Received during Query Execution:" + e.getMessage());
			e.printStackTrace();
		}
		
		//return returnVal;
		return QueryResultValue;
		
	}
}



