package com.controller;

import com.common.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.security.UrlAuthenticationSuccessHandler;
import com.unixconnection.SshClient;
import com.unixconnection.UnixProcessOutput;
import com.unixconnection.Unixprocessdetail;
import com.unixconnection.Unixprocessdetails;
import com.weburl.reader.AppViewBatchDetails;
import com.weburl.reader.impl.WebPageURLReader;
import com.xmlreader.ExecuteUnixXMLReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.log4j.Logger;


@Controller
public class AppViewController {

	static Logger log = Logger.getLogger(AppViewController.class.getName());


	@RequestMapping(value = "/AppView", method = RequestMethod.GET)
	public ModelAndView monitorUnixProcess(
	@RequestParam(value = "appid", required = false) String appviewid,
     Model model, HttpServletResponse response) {

		log.info("inisde AppViewJson method");
		List <AppViewBatchDetails> appviewbatchlist = null;
		
	       try {

	    	   if (!appviewid.isEmpty())	{
	    		   
	    		   WebPageURLReader webpagereader = new WebPageURLReader();
	    		   webpagereader.extractNeededAppview(appviewid);
	    		   webpagereader.executeRetrieveAppviewDetailsWrapper();
	    		   appviewbatchlist = webpagereader.returnMatchedAppviewJobs();
	    		   
	    	   }
				//return the dummy outputdisplay page, show nothing
				return (new ModelAndView(CommonConstants.AppViewDisplayPage,"appviewbatch",appviewbatchlist));
	       }
	       catch (Exception e){
	    	   log.error("exception received here!!!!");
	       }

		  return (new ModelAndView(CommonConstants.OutputDisplayPage,"",""));
	}
	
	@RequestMapping(value = "/AppViewJson",method=RequestMethod.GET,produces={"application/xml", "application/json"})
	public ModelAndView monitorUnixProcessJson(
	@RequestParam(value = "appid", required = false) String appviewid,
     Model model, HttpServletResponse response) {

		log.info("inisde AppViewJson json method");
		List <AppViewBatchDetails> appviewbatchlist = null;
		String json ="";
		
	       try {

	    	   if (!appviewid.isEmpty())	{
	    		   
	    		   WebPageURLReader webpagereader = new WebPageURLReader();
	    		   webpagereader.extractNeededAppview(appviewid);
	    		   webpagereader.executeRetrieveAppviewDetailsWrapper();
	    		   appviewbatchlist = webpagereader.returnMatchedAppviewJobs();
	    		   
	    		    ObjectMapper mapper = new ObjectMapper();
		    		   
	    		    for (int size = 0 ; size < appviewbatchlist.size() ; size++){
	    		    	
			   			// Convert object to JSON string
			   			String jsonInString = mapper.writeValueAsString(appviewbatchlist.get(size));
			   			log.info("json to string:" + jsonInString);
	    		    }

	    		    json = new Gson().toJson(appviewbatchlist);
	    		    log.info("json string info:" + json);
	    		    //JSONArray array = new JSONArray(json);
	    		    
		   			/***
		   			 * INFO  AppViewController - json string info:[{"jobName":"CTTCVS007D","description":"Start ClearVision SFE - Exchange Server","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS007D","description":"Start ClearVision SFE - Exchange Server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"LDOCVG008D","description":"Start Stream Gateway for SFE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG008D","description":"Start Stream Gateway for SFE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVS008D","description":"Start ClearVision NZFOE - Exchange Server","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS008D","description":"Start ClearVision NZFOE - Exchange Server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"LDOCVG009D","description":"Start Stream Gateway for NZFOE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG009D","description":"Start Stream Gateway for NZFOE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVS010D","description":"Start ClearVision TOCOM - Exchange server","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS010D","description":"Start ClearVision TOCOM - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"LDOCVG003D","description":"Start Stream Gateway for TOCOM","oDATE":"04/26","startTime":"04/27 06:00:13","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG003D","description":"Start Stream Gateway for TOCOM","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVS005D","description":"Start ClearVision TFX - Exchange server","oDATE":"04/26","startTime":"04/27 07:15:04","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS005D","description":"Start ClearVision TFX - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0715","runDuration":"N  "},{"jobName":"CTTCVS004D","description":"Start ClearVision TSE - Exchange server","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS004D","description":"Start ClearVision TSE - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"LDOCVG007D","description":"Start Stream Gateway for TSE","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG007D","description":"Start Stream Gateway for TSE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM005D","description":"Start ClearVision SFE/NZ - MemberServer","oDATE":"04/26","startTime":"04/27 02:00:07","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM005D","description":"Start ClearVision SFE/NZ - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0200","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVM004D","description":"Start ClearVision OSE/TSE/TFX - MemberServer","oDATE":"04/26","startTime":"04/27 06:05:03","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM004D","description":"Start ClearVision OSE/TSE/TFX - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0605","runDuration":"N  "},{"jobName":"CTTCVM007D","description":"Start ClearVision TOCOM - MemberServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM007D","description":"Start ClearVision TOCOM - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVC006D","description":"Start ClearVision TOCOM - SecurityServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC006D","description":"Start ClearVision TOCOM - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM006D","description":"Start ClearVision SGX - MemberServer","oDATE":"04/26","startTime":"04/27 07:05:02","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM006D","description":"Start ClearVision SGX - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0705","runDuration":"N  "},{"jobName":"CTTCVC005D","description":"Start ClearVision SGX - SecurityServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC005D","description":"Start ClearVision SGX - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVS003D","description":"Start ClearVision HKFE - Exchange server","oDATE":"04/26","startTime":"04/27 04:58:00","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS003D","description":"Start ClearVision HKFE - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0458","runDuration":"N  "},{"jobName":"CTTCVM001D","description":"Start ClearVision HKFE - MemberServer","oDATE":"04/26","startTime":"04/27 03:05:01","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM001D","description":"Start ClearVision HKFE - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0305","runDuration":"N  "},{"jobName":"CTTCVC001D","description":"Start ClearVision HKFE - SecurityServer","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC001D","description":"Start ClearVision HKFE - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVC003D","description":"Start ClearVision SGX - Exchange server","oDATE":"04/26","startTime":"04/27 08:28:01","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC003D","description":"Start ClearVision SGX - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0828","runDuration":"N  "},{"jobName":"CTTCVS001D","description":"Start ClearVision 4M01","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS001D","description":"Start ClearVision 4M01","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS002D","description":"Start ClearVision 4M02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS002D","description":"Start ClearVision 4M02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVM002D","description":"Start ClearVision 4M - MemberServer","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM002D","description":"Start ClearVision 4M - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVC002D","description":"Start ClearVision 4M - SecurityServer","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC002D","description":"Start ClearVision 4M - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS006D","description":"Start ClearVision Welcome Table","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS011D","description":"Start ClearVision KSE 4M","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS011D","description":"Start ClearVision KSE 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM008D","description":"Start ClearVision KSE 4M - MemberServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM008D","description":"Start ClearVision KSE 4M - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVB001D","description":"Start ExportBack for HKFE","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB001D","description":"Start ExportBack for HKFE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB002D","description":"Start ExportBack for HKFE Equities","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB002D","description":"Start ExportBack for HKFE Equities","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB003D","description":"Start ExportBack for TOCOM 4M","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB003D","description":"Start ExportBack for TOCOM 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB004D","description":"Start ExportBack for SFE, NZFOE, 4M","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB004D","description":"Start ExportBack for SFE, NZFOE, 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVB005D","description":"Start ExportBack for SFE, NZFOE, 4M Equities","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB005D","description":"Start ExportBack for SFE, NZFOE, 4M Equities","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVB006D","description":"Start ExportBack for SGX","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB006D","description":"Start ExportBack for SGX","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB007D","description":"Start ExportBack for TOCOM CV","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB007D","description":"Start ExportBack for TOCOM CV","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVB008D","description":"Start ExportBack for OSE, TFX, TSE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB008D","description":"Start ExportBack for OSE, TFX, TSE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVF001D","description":"Start EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF001D","description":"Start EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF002D","description":"Start F2B Orders EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF002D","description":"Start F2B Orders EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF003D","description":"Start Reporting EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF003D","description":"Start Reporting EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF004D","description":"Start Allocation EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF004D","description":"Start Allocation EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVF005D","description":"Start Utility EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF005D","description":"Start Utility EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "}]
		   			 * 
		   			 **/

		   			/**
		   			// Convert object to JSON string and pretty print
		   			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
		   			System.out.println(jsonInString);
		   			*/
	    	   }
				//return the dummy outputdisplay page, show nothing
				//return (new ModelAndView("AppviewDisplayJsonPage","appviewbatch",appviewbatchlist));
	    	      return (new ModelAndView("AppviewDisplayJsonPage","appviewbatch",json));
				
	       }
	       catch (Exception e){
	    	   log.error("exception received here!!!!");
	       }

		  return (new ModelAndView(CommonConstants.OutputDisplayPage,"",""));
	}

	@RequestMapping(value = "/AppViewJsonInput",method=RequestMethod.GET,produces={"application/xml", "application/json"})
	public ModelAndView monitorUnixProcessJsonInput(
	@RequestParam(value = "appid", required = false) String appviewid,
     Model model, HttpServletResponse response) {

		log.info("inisde AppViewJson json method");
		List <AppViewBatchDetails> appviewbatchlist = null;
		
	       try {

				return (new ModelAndView("AppviewDisplayJsonPage","",""));
	       }
	       catch (Exception e){
	    	   log.error("exception received here!!!!");
	       }

		  return (new ModelAndView(CommonConstants.OutputDisplayPage,"",""));
	}
}
