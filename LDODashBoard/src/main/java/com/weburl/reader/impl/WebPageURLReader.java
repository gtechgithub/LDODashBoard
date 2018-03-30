package com.weburl.reader.impl;

import java.io.BufferedReader;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.CommonConstants;
import com.weburl.reader.AppViewBatchDetails;
import com.weburl.reader.AppviewXML;
import com.xmlreader.ExecuteXMLReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebPageURLReader {
	
	private List <AppViewBatchDetails> appviewbatchList;
	private List <AppviewXML> appviewxml;
	
	public List<AppViewBatchDetails> getAppviewbatchList() {
		return appviewbatchList;
	}

	public List <AppviewXML> getAppviewxml() {
		return appviewxml;
	}


	
	static Logger log = Logger.getLogger(WebPageURLReader.class.getName());

	
	public void extractNeededAppview(String appid){
		
		AppViewReaderImpl  appview  = new AppViewReaderImpl();
		appview.populateFromXML();
		
		appviewxml = appview.getAppropriateAppView(appid);
		
	}
	
	
	public void executeRetrieveAppviewDetailsWrapper(){
		
    	appviewbatchList = new   ArrayList <AppViewBatchDetails>();
		for (int index = 0; index < appviewxml.size(); index++){
			executeRetrieveAppviewDetails(appviewxml.get(index).getUrlLink());
		}
	}
	
	public void executeRetrieveAppviewDetails(String urllink){
		
        URL url = null;
        HttpURLConnection urlConn = null;
        
		  try {
	            //connect the url and get the documents content
	        	Document doc = Jsoup.connect(urllink).timeout(30000).get();
	        	
	        	boolean isStartAdd = false;
	        	
	        	for (Element elementtable : doc.select("table")){
	        		AppViewBatchDetails viewDetails  = null;
		        	for( Element elementtr : elementtable.select("tr") )
		        	{
		        		viewDetails = new AppViewBatchDetails();
		        		int index =0;
		        		
		        		for (Element elementtd : elementtr.select("td")){
		            	    
		            	    if (index==0){
		            	    	//need to do this since trim string function only does '\u0020' 
		            	    	viewDetails.setJobName(elementtd.text().replaceAll("\\u00A0", ""));
		            	    }
		            	    else if (index ==1){
		            	    	viewDetails.setDescription(elementtd.text().replaceAll("\\u00A0", ""));
		            	    }
		            	    else if (index ==2){
		            	    	viewDetails.setoDATE(elementtd.text());
		            	    }
		            	    else if (index ==3){
		            	    	viewDetails.setStartTime(elementtd.text());
		            	    }
		            	    else if (index ==4){
		            	    	viewDetails.setEndTime(elementtd.text());
		            	    }
		            	    else if (index ==5){
		            	    	viewDetails.setStatus(elementtd.text());
		            	    }
		            	    else if (index ==6){
		            	    	viewDetails.setRunDuration(elementtd.text());
		            	    }
		            	    else if (index ==7){
		            	    	viewDetails.setRunDuration(elementtd.text());
		            	    }
		            	    ++index;
		        		}
		        		
		        		if (!isStartAdd){
			        		isStartAdd = isGoodtoExtract(viewDetails);
			        		continue;
		        		}
		        		
		        		//add the appview details for each iteration
		        		appviewbatchList.add(viewDetails);
		        	}
	        	}            	
	        	
	 
	        } catch (Exception ioe) {
	            System.out.println(ioe.getMessage());
	            ioe.printStackTrace();
	        }
	}
	
	public boolean isGoodtoExtract(AppViewBatchDetails details){
		boolean startextract = false;
		
		//if the field satifies then we can extract from the below
		if ( (details.getJobName().equals("Job Name")) &&
			 (details.getDescription().equals("Description")) &&
			 (details.getoDATE().equals("ODATE")) &&
			 (details.getStartTime().equals("Start")) &&
			 (details.getEndTime().equals("End")) &&
			 (details.getStatus().equals("Status/FrmTm")) ){
			log.info("start extract is set to true");
			startextract = true;
		}
		
		return startextract;
	}
	
	public List <AppViewBatchDetails> returnMatchedAppviewJobs(){
		List <AppViewBatchDetails> appbatcDetailsList = new  ArrayList  <AppViewBatchDetails>();
		AppViewBatchDetails appviewdetail =  null;
		
		
		//iterate for the each job list
		for (int appviewsize = 0; appviewsize < appviewxml.size(); appviewsize++){

			for (int joblistsize =0; joblistsize < appviewxml.get(appviewsize).getJobList().size(); joblistsize++ ){
				
				String jobname   = appviewxml.get(appviewsize).getJobList().get(joblistsize).getJobName();
				
				int jobcountMatch = 0;
				//find the match for the job list extract
				for( int size=0 ;size < appviewbatchList.size() ; size++ ){
					
					//required job matches with the extractlist of jobs
					if (appviewbatchList.get(size).getJobName().equals(jobname)){
						appbatcDetailsList.add (appviewbatchList.get(size));
						++jobcountMatch;
					}
				}
				
				//job not available in the list, then we need to add them saying not found
				if (jobcountMatch == 0){
					appviewdetail = new AppViewBatchDetails();
					appviewdetail.setJobName(jobname);
					appviewdetail.setDescription(appviewxml.get(appviewsize).getJobList().get(joblistsize).getBriefDescription());
					appviewdetail.setStatus("NOT FOUND IN APPVIEW");
					appbatcDetailsList.add (appviewdetail);
				}
			}
		}
		return appbatcDetailsList;
	}
	
	public static void main(String[] args) {
		
		WebPageURLReader urlreader = new WebPageURLReader();
		urlreader.extractNeededAppview(CommonConstants.AppViewCDR);
		urlreader.executeRetrieveAppviewDetailsWrapper();
		List<AppViewBatchDetails> appviewexecuteddetails  = urlreader.getAppviewbatchList();
		urlreader.log.info("appviewexecuteddetails size:" + appviewexecuteddetails.size());
		
		
		appviewexecuteddetails = urlreader.returnMatchedAppviewJobs();
		
		urlreader.log.info("appviewexecuteddetails size:" + appviewexecuteddetails.size());

		for( int size=0 ;size < appviewexecuteddetails.size() ; size++ ){
			urlreader.log.info("job Name:" + appviewexecuteddetails.get(size).getJobName());
			urlreader.log.info("desc:" + appviewexecuteddetails.get(size).getDescription());
			urlreader.log.info("oDate:" + appviewexecuteddetails.get(size).getoDATE());
			urlreader.log.info("startime:" + appviewexecuteddetails.get(size).getStartTime());
			urlreader.log.info("endtime:" + appviewexecuteddetails.get(size).getEndTime());
			urlreader.log.info("runduration:" + appviewexecuteddetails.get(size).getRunDuration());
			urlreader.log.info("status:" + appviewexecuteddetails.get(size).getStatus());
			urlreader.log.info("cyclic:" + appviewexecuteddetails.get(size).getCyclicJob());

		}
		
    }//end main
}
