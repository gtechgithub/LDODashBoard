package com.weburl.reader.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.common.CommonConstants;
import com.unixconnection.Unixprocessdetail;
import com.unixconnection.Unixprocessdetails;
import com.utils.CommonUtils;
import com.weburl.reader.AppviewXML;
import com.weburl.reader.AppviewXMLWrapper;



public class AppViewReaderImpl {

	private AppviewXMLWrapper adaptedWrapper;
	
	static Logger log = Logger.getLogger(AppViewReaderImpl.class.getName());
	
	
	public void populateFromXML(){
		
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(AppviewXMLWrapper.class);

			
			
			String xmlFileNamePath = getXMLFileCompletePath();
			File xmlFile = new File(xmlFileNamePath);
			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			
			
			/*** to be commented for hard code testing 
			File xmlFile = new File("src/main/resources/" + "AppViewXMLContent.xml");
			
			to be commented till for hard code testing ***/
			
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			adaptedWrapper = (AppviewXMLWrapper) unmarshaller.unmarshal(xmlFile);

		} catch (JAXBException e) {
			log.error("inside the exception received!!!!!");
			e.printStackTrace();
		}
	}
	
	public List <AppviewXML> getAppropriateAppView(final String idName){
		
		List <AppviewXML> appviewXML = null;

		if (!idName.isEmpty()) {
			appviewXML = new   ArrayList <AppviewXML>() ;
			for(AppviewXML detail : adaptedWrapper.getAppview()) {
				
			   //if id name equals requested appview return it
	           if( idName.equals(detail.getId())){
	        	   
	        	   log.info("id name matched" + idName);
	        	   appviewXML.add(detail);
	        	   
	        	   //appviewXML =  detail;
	        	   //break;
	           }
			}
			
		}
		return appviewXML;
	}
	
	private String getXMLFileCompletePath()
	{
		String	xmlFileNamePath = "";	
		try
		{
			
			String classFilePath = AppViewReaderImpl.class.getClassLoader().getResource("com/weburl/reader/impl/AppViewReaderImpl.class").toString();
			log.info("class display:" + AppViewReaderImpl.class.getClassLoader().getResource("com/weburl/reader/impl/AppViewReaderImpl.class"));
			
			 xmlFileNamePath = CommonUtils.extractFilePathWebInf(classFilePath) + "/Resources/AppViewXMLContent.xml";
			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception Received:"+ e.getMessage());
		}
		
		return xmlFileNamePath;

	}
	
	public static void main (String args[]){
		AppViewReaderImpl  appview  = new AppViewReaderImpl();
		appview.populateFromXML();
		
		List <AppviewXML> view  = appview.getAppropriateAppView(CommonConstants.AppViewLDMRec1);
		
		for (int index  =0 ; index < view.size(); index++){
			AppViewReaderImpl.log.info("id " + view.get(index).getId());
			AppViewReaderImpl.log.info("url " + view.get(index).getUrlLink());
			AppViewReaderImpl.log.info("job list size" + view.get(index).getJobList().size());
		}
		
		
	}
}
