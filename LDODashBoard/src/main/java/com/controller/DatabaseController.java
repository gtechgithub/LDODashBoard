package com.controller;

import com.common.CommonConstants;
import com.connection.impl.DBCheckoutImpl;
import com.connection.impl.DBCheckoutResults;
import com.connection.impl.LDMConnectionImpl;
import com.connection.impl.QueryExecutionResults;
import com.connection.impl.QueryExecutorImpl;
import com.connection.impl.QuerySpecifierWrapper;
import com.connection.impl.QuerySpecifierWrapperList;
import com.dao.AuditDisplayPostClass;
import com.dao.CVAuditDisplayResponse;
import com.dao.CVAuditStaticUserClass;
import com.dao.CVModifClass;
import com.dao.ClusterInfo;
import com.dao.Progs;
import com.dao.SysControl;
import com.dao.impl.CVAuditStaticUserParams;
import com.google.gson.Gson;
import com.xmlreader.Server;
import com.xmlreader.ServerDetails;
import com.xmlreader.ServerMapper;

import javassist.bytecode.Descriptor.Iterator;

import com.xmlreader.ExecuteXMLReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.common.CommonConstants;
import com.security.UrlAuthenticationSuccessHandler;
import org.apache.log4j.Logger;
import com.connection.impl.LDMConnectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Controller
public class DatabaseController {

	static Logger log = Logger.getLogger(DatabaseController.class.getName());
	
	@Autowired DBCheckoutImpl DBCheckoutBean;
	
	@Autowired
	QuerySpecifierWrapperList querySpecifierWrapperListBean;
	
	@Autowired
	QueryExecutorImpl queryexecutorimplbean;

	/**	
	@Autowired
	CVAuditStaticUserParamsSetClass CVAuditStaticUserParamsSetClassBean; 
	

	@Autowired
	CVAuditStaticUserParams CVAuditStaicNamesBean;
	**/ 
	
	private StringBuffer strbuf;

	/**
	 * 
	 * Setting the modal attribute, with the reason, it can be used in the different page/view
	 * @return
	 */
	@ModelAttribute("QueryLogs")
	public StringBuffer getQueryLogsMethod()
	{
		
		log.info("inside the getQueryLogsMethod method");
		return this.strbuf;
	}
	
	@RequestMapping(value = "ExecuteQueryResults", method = RequestMethod.GET)
	public ModelAndView ExecuteQueryFromID(
		@RequestParam(value = "QueryIDName", required = false) String QueryIDName,
		HttpSession session,HttpServletResponse response) {
			
	log.info("inside ExecuteQueryFromID method");
	
	String responseText ="";

	if (! QueryIDName.isEmpty()){
		ModelAndView model = null;
		QueryExecutionResults execResults = null;
		//QueryExecutorImpl execImpl = new QueryExecutorImpl();
		//QueryExecutionResults Executionresults = null;
		
		if (QueryIDName.equals(CommonConstants.GCMTLMClusterInfo)) {

			log.info("inside GCMTLMClusterInfo method");

			//List <QuerySpecifierWrapper> listWrapper = querySpecifierWrapperListBean.getQuerySpecifierWrapperList();
					
			//log.info(" QuerySpecifierWrapper list count:" + listWrapper.size());
			
			
			//get the exeution query for passing param 
			//QueryExecutionResults execResults = execImpl.executeQueryWrapperByQueryID(CommonConstants.GCMTLMClusterInfo);
			execResults = queryexecutorimplbean.executeQueryWrapperByQueryID(CommonConstants.GCMTLMClusterInfo);
			model = new ModelAndView(CommonConstants.GlobalCashmanClusterinfoDisplayPage);
			
			List <ClusterInfo> listobject = execResults.getQueryRowColResult();
			log.info("execResults list object size:" + listobject.size());

			log.info("returning the model object for GCMTLMClusterInfo");
		}
		else if (QueryIDName.equals(CommonConstants.GCMTLMMactchProgs)) {
			log.info("inside GCMTLMMactchProgs method");
			
			execResults = queryexecutorimplbean.executeQueryWrapperByQueryID(CommonConstants.GCMTLMMactchProgs);
			model = new ModelAndView(CommonConstants.GlobalCashmanProgsDisplayPage);
			
			List <Progs> listobject = execResults.getProgsRowColResult();
			log.info("execResults list object size:" + listobject.size());
			log.info("returning the model object for GCMTLMMactchProgs");
		}
		else if (QueryIDName.equals(CommonConstants.GCMTLMSysControl)) {
			log.info("inside GCMTLMSysControl method");
			
			execResults = queryexecutorimplbean.executeQueryWrapperByQueryID(CommonConstants.GCMTLMSysControl);
			model = new ModelAndView(CommonConstants.GlobalCashmanSysControlDisplayPage);
			
			List <SysControl> listobject = execResults.getSysControlRowColResult();
			log.info("execResults list object size:" + listobject.size());
			log.info("returning the model object for GCMTLMSysControl");
		}
		
		model.addObject("QueryExecutionResults", execResults);
		log.info("execResults query description:" + execResults.getQueryDescription());
		log.info(" execResults query column names size:" + execResults.getQueryColumnNames().size());
		
		return model;
	}
	
	//this is an empty page
	return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);

}

	@RequestMapping(value = "databaseCheckout", method = RequestMethod.GET)
	public ModelAndView srcDatabaseCheckoutMethod(
			@RequestParam(value = "dbCheckout", required = false) String dbCheckout,
			@ModelAttribute("QueryLogs") StringBuffer queryLogs, 
			HttpSession session,HttpServletResponse response) {
		
		log.info("inside databaseCheckout method");
		
		String responseText ="";

			
		if (dbCheckout != null) {

			ModelAndView model = null;
			List<DBCheckoutResults> dbCheckoutResults = null;
			if (dbCheckout.equals(CommonConstants.LDMDbName)) {
				log.info("inside  dbCheckout LDM routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.LDMDbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);

			}
			else if (dbCheckout.equals(CommonConstants.OPUSdbName)) {
				log.info("inside  dbCheckout OPUS routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.OPUSdbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);

			}
			else if (dbCheckout.equals(CommonConstants.CDRdbName)) {
				log.info("inside  dbCheckout CDR routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.CDRdbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else if (dbCheckout.equals(CommonConstants.CVCheckoutEmeaDbName)) {
				log.info("inside  dbCheckout CV EMEA routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.CVCheckoutEmeaDbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else if (dbCheckout.equals(CommonConstants.CVCheckoutAmericaDbName)) {
				log.info("inside  dbCheckout CV America routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.CVCheckoutAmericaDbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else if (dbCheckout.equals(CommonConstants.TLMdbName)) {
				log.info("inside  dbCheckout TLM routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.TLMdbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else if (dbCheckout.equals(CommonConstants.GlobalCashTLMdbName)) {
				log.info("inside  dbCheckout Global cash man routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.GlobalCashTLMdbName);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else if (dbCheckout.equals(CommonConstants.MatchDerivativeDBCheckout)) {
				log.info("inside  dbCheckout match derivative routine");
				dbCheckoutResults = dbCheckoutProcess(CommonConstants.MatchDerivativeDBCheckout);
				model = new ModelAndView(CommonConstants.IndDatabaseCheckoutDisplaPage);
			}
			else {
				log.info("inside  dbCheckout routine");
				dbCheckoutResults = dbCheckoutProcess();
				model = new ModelAndView(CommonConstants.DatabaseCheckoutDisplaPage);
			}
			strbuf = formSinglequeryResults(dbCheckoutResults);

			// setting the modal attribute
			model.addObject("QueryLogs", strbuf);
			model.addObject("DatabaseCheckout", dbCheckoutResults);
			log.info("QueryLogs:" + strbuf.toString());
			return model;
		}
		
		//return "L1OutputDisplayPage";
		//return new ModelAndView(CommonConstants.L1OutputDisplayPage, "message", responseText);

		//output display page is the common display page
		return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);

	}

	@RequestMapping(value = "showDatabaseCheckoutLogs", method = RequestMethod.GET)
	public ModelAndView srcShowDatabaseCheckoutLogs() {
		
		log.info("inside srcShowDatabaseCheckoutLogs method");
		
		ModelAndView modelNew = new ModelAndView(CommonConstants.DatabaseCheckoutLogsDisplaPage);
		//modelNew.addObject("QueryLogs",logs.toString());

		return modelNew;
		
	}

	
	private List <DBCheckoutResults> dbCheckoutProcess() {
		
		List <DBCheckoutResults> dbCheckoutResults = null;

		try
		{
			dbCheckoutResults = new ArrayList <DBCheckoutResults>();
			dbCheckoutResults = DBCheckoutBean.getCheckoutResults();
			return dbCheckoutResults;
		}
		catch(Exception e)
		{
			log.error("Error received in dbCheckoutProcess:" + e.getMessage());
			e.printStackTrace();
		}
		return dbCheckoutResults;
	}
	
	private StringBuffer formSinglequeryResults(List <DBCheckoutResults> dbCheckoutResults)
	{
		StringBuffer strBuf   = new StringBuffer();
		
		log.info("Inside formSinglequeryResults");
		try {
			
			//strBuf.append ("<HTML>");
			//strBuf.append ("<BODY>");

			for (DBCheckoutResults dbcheckout : dbCheckoutResults) {
				
				log.info ("log append:" + dbcheckout.getQuerylogs());
				strBuf.append(dbcheckout.getQuerylogs().toString());
			}
			
			//strBuf.append ("</BODY>");
			//strBuf.append ("</HTML>");
			return strBuf;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		return strBuf;
	}
	
	private List <DBCheckoutResults> dbCheckoutProcess(String dbName) {
		
	List <DBCheckoutResults> dbCheckoutResults = null;

	try
	{
		dbCheckoutResults = new ArrayList <DBCheckoutResults>();
		dbCheckoutResults = DBCheckoutBean.getCheckoutResults(dbName);
		return dbCheckoutResults;
	}
	catch(Exception e)
	{
		log.error("Error received in dbCheckoutProcess:" + e.getMessage());
		e.printStackTrace();
	}
	return dbCheckoutResults;
}
	
	
	@RequestMapping(value = "AuditDisplay", method = RequestMethod.GET)
	public ModelAndView auditDisplay(
			HttpSession session,HttpServletResponse response) {
		
		log.info("inisde AuditDisplay method");

		return (new ModelAndView(CommonConstants.AuditDisplayPage));
	}

	
	//@RequestMapping(value = "/AuditDisplayPostFinalResponse", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/AuditDisplayResultPage", method = RequestMethod.GET)
	public ModelAndView auditDisplayResponseFinal(
	@RequestParam(value = "DisplayResponse", required = false) CVAuditDisplayResponse displayResponse){
		
		log.info("inisde auditDisplayResponseFinal method");

		return (new ModelAndView("AuditDisplayResultPage","DisplayResponse",displayResponse));
	}
	
	@RequestMapping(value = "/AuditDisplayPostResponse", method = RequestMethod.GET)
	public ModelAndView auditDisplayResponse(
	@RequestParam(value = "argument1", required = false) String argument1){

		
		log.info("inisde auditDisplayResponse method");
		
		String json = "";
				
		try {
			
			//convert string to json
		    json = "[" + argument1 + "]";
		    
		    log.info("argument1:" + argument1);
		    log.info("argument1 json:" + json);
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
				
		return (new ModelAndView("AuditDisplayResultPage","DisplayResponse",json));
		//return (new ModelAndView("L1OutputDisplayPage","message",json));
	}
	
	@RequestMapping(value = "/AuditDisplayPost", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String auditDisplayPost(
	@RequestBody  AuditDisplayPostClass postClass, UriComponentsBuilder ucb) {
		
		
		
		
		QueryExecutionResults execResults = null;
		
		log.info("inisde AuditDisplayPost method");
		log.info("auditdisplaypostclass name:" + postClass.getName());
		log.info("auditdisplaypostclass account:" + postClass.getAccount());
		log.info("auditdisplaypostclass database:" + postClass.getDatabase());
        
		CVAuditDisplayResponse auditResponse =  new CVAuditDisplayResponse();
		
		CVAuditStaticUserParams cvstaticparams =  new CVAuditStaticUserParams();
		cvstaticparams.setNames(DBCheckoutBean.retrieveStringTokenQuery(postClass.getAccount()," "));
		
		execResults = queryexecutorimplbean.executeQueryWrapperByQueryID(CommonConstants.CVAuditStaticUser,postClass.getDatabase(),cvstaticparams);
		List <CVAuditStaticUserClass> listobject = execResults.getCvAuditStaticRowColRslt();
		log.info("CVAuditStaticUser execResults list object size:" + listobject.size());
		auditResponse.setCvAuditClassList(listobject);

		log.info("Converting the auditresponse object to json string");
        String json = new Gson().toJson(auditResponse);
        
        log.info("printing the json:" + json);

       
        return json;
	}  

}


