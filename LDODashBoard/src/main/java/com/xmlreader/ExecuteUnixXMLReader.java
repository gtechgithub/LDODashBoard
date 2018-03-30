package com.xmlreader;

import com.xmlreader.ServerDetails;
import com.controller.DisplayController;
import com.unixconnection.Unixprocessdetail;
import com.unixconnection.Unixprocessdetails;
import com.utils.CommonUtils;
import com.xmlreader.Server;
import java.io.File;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class ExecuteUnixXMLReader {
	
	private Unixprocessdetails adaptedWrapper;
	
	static Logger log = Logger.getLogger(ExecuteUnixXMLReader.class.getName());


	public Unixprocessdetails getAdaptedWrapper() {
		return adaptedWrapper;
	}

	public void setAdaptedWrapper(Unixprocessdetails adaptedWrapper) {
		this.adaptedWrapper = adaptedWrapper;
	}

	
	public void populateFromXML(){
		
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(Unixprocessdetails.class);
			
			String xmlFileNamePath = getXMLFileCompletePath();
			File xmlFile = new File(xmlFileNamePath);

			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			adaptedWrapper = (Unixprocessdetails) unmarshaller.unmarshal(xmlFile);

			/***
			for(Unixprocessdetail server : adaptedWrapper.getUnixProcessExecutors()) {
	            System.out.println("job description:"+ server.getJobDescription());
			}
			***/
		
		} catch (JAXBException e) {
			log.error("inside the exception received!!!!!");
			e.printStackTrace();
		}
	}
	
	public Unixprocessdetail getAppropriateUnixDetail(final String jobName){
		Unixprocessdetail processDetail = null;

		if (!jobName.isEmpty()) {
			for(Unixprocessdetail detail : adaptedWrapper.getUnixProcessExecutors()) {
				
			   //if job name equals return the process detail
	           if( jobName.equals(detail.getJobName())){
	        	   log.info("job name matched:" + jobName);
	        	   processDetail =  detail;
	        	   break;
	           }
			}
			
		}
		return processDetail;
	}

	private String getXMLFileCompletePath()
	{
		String	xmlFileNamePath = "";	
		try
		{
			
			String classFilePath = ExecuteUnixXMLReader.class.getClassLoader().getResource("com/xmlreader/ExecuteUnixXMLReader.class").toString();
			log.info("class display:" + ExecuteUnixXMLReader.class.getClassLoader().getResource("com/xmlreader/ExecuteUnixXMLReader.class"));
			
			 xmlFileNamePath = CommonUtils.extractFilePathWebInf(classFilePath) + "/Resources/UnixProcessExecute.xml";
			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception Received:"+ e.getMessage());
		}
		
		return xmlFileNamePath;

	}
	
}
