package com.controller;

import com.common.CommonConstants;
import com.connection.impl.DBCheckoutImpl;
import com.connection.impl.DBCheckoutResults;
import com.connection.impl.LDMConnectionImpl;
import com.implementation.LnkConnectionEngine;
import com.implementation.LnkConnectionResponse;
import com.xmlreader.Server;
import com.xmlreader.ServerDetails;
import com.xmlreader.ServerMapper;

import javassist.bytecode.Descriptor.Iterator;

import com.xmlreader.ExecuteXMLReader;

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

@Controller
public class DisplayController {

	static Logger log = Logger.getLogger(DisplayController.class.getName());
	
	@Autowired
	private LDMConnectionImpl LdmDatabaseBean;
	
	//@Autowired DBCheckoutImpl DBCheckoutBean;

	@RequestMapping(value = "L1OutputDisplayPage", method = RequestMethod.GET)
	public ModelAndView srcOutputDisplayMethod(
			@RequestParam(value = "ldmUnprocessedLink", required = false) String clicked,
			@RequestParam(value = "gcmmLink2", required = false) String clicked1,
			HttpSession session,HttpServletResponse response) {
		log.info("inside DisplayController method");
		
		StringBuffer responseBody = new  StringBuffer() ;
		String responseText ="";

		
		if (clicked != null) {
			log.info("inside clicked routine");
			log.info("clicked value:" + clicked);
			
			responseBody = processldmUnprocessedLink();
			responseText = responseBody.toString();
			
			//return new ModelAndView(CommonConstants.L1OutputDisplayPage, "message", responseText);
			return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);
		}
	
		//return "L1OutputDisplayPage";
		//return new ModelAndView(CommonConstants.L1OutputDisplayPage, "message", responseText);
		return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);
	}
	
	@RequestMapping(value = "L2OutputDisplayPage", method = RequestMethod.GET)
	public ModelAndView l2srcOutputDisplayMethod(
			HttpSession session,HttpServletResponse response) {
		log.info("inside L2 DisplayController method");
		
		String responseText ="";
		
		//return new ModelAndView(CommonConstants.L2OutputDisplayPage, "message", responseText);
		return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);
	}
	
	//@RequestMapping(value = "hostCheckout", method = RequestMethod.GET)
	@RequestMapping(value = "hostCheckout")
	public ModelAndView srcHostCheckoutMethod(
			@RequestParam(value = "checkout", required = false) String checkout,
			HttpSession session,HttpServletResponse response) {
			//@ModelAttribute("captionName") String captionName,
			//final RedirectAttributes redirectAttributes) {
		
		log.info("inside hostCheckout method");
		
		String responseText ="";

		if (checkout != null) {
			log.info("inside  checkout routine");
			ModelAndView model = new ModelAndView(CommonConstants.HostCheckouttDisplayPage); 
			String captionName = checkout + " Server Checkout ";
			List <ServerMapper> hostCheckoutResults = hostCheckoutProcess(checkout);
			model.addObject("captionName", captionName);
			model.addObject("Server", hostCheckoutResults);
			//return new ModelAndView(CommonConstants.HostCheckouttDisplayPage, "Server", hostCheckoutResults);
			return model;
		}
		
		//return "L1OutputDisplayPage";
		//return new ModelAndView(CommonConstants.L1OutputDisplayPage, "message", responseText);
		return new ModelAndView(CommonConstants.OutputDisplayPage, "message", responseText);
	}

	@RequestMapping(value = "passwordlessremote")
	public String passwordLessRemoteMethod(
			@RequestParam(value = "checkout", required = false) String checkout,
			HttpSession session,HttpServletResponse response) {
		
		log.info("inside passwordlessremote method");
		
		String responseText ="";

		if (checkout != null) {
			log.info("inside  passwordlessremote checkout routine");
			
			return "redirect:/resource/run.bat";
			
			//return "redirect:file:////C://Users//gopalakrishnan//Desktop//test.html";
		}
		
		//return "L2OutputDisplayPage";
		return CommonConstants.OutputDisplayPage;
		//return new ModelAndView(CommonConstants.L2OutputDisplayPage, "message", responseText);

	}
	
	
	private StringBuffer processldmUnprocessedLink() {
		StringBuffer responseBody = new StringBuffer();
		String responseText = "";
		int returnValue = -1;

		responseText = "<div style='text-align:center;'>"
				+ "<h4 style='color:yellow'>  <u> Showing the output of LDM Unprocessed </u></h4> ";

		responseBody.append(responseText);
		
		try
		{
			returnValue = LdmDatabaseBean.checkUnProcessed();
		}
		catch(Exception e)
		{
			log.error("Error received in processldmUnprocessedLink:" + e.getMessage());
			e.printStackTrace();
		}

		responseText = "<h4 style=color:white>Unprocessed Count:[" + returnValue + "] </h4> ";
		responseBody.append(responseText);

		if (returnValue < 0) {
			responseText = "<br> <p style=color:white> <i> Please check Tomcat logs, some issue </i> </p> ";
			responseBody.append(responseText);
		}

		// close the div tag
		responseBody.append("</div>");
		
		return responseBody;

	}
	

 
	private List<ServerMapper> hostCheckoutProcess(String xmlFileNamePath)
	{
		List<Server>  servers = null;
		List<ServerMapper> servermapper =  null;
		
		try
		{
			
			ExecuteXMLReader callxmlReader = new ExecuteXMLReader();
			//xmlFileNamePath = "/resource/" + xmlFileNamePath +  "serverDetails.xml";
			
			
			//xmlFileNamePath = "/src/main/resources/" + xmlFileNamePath +  "serverDetails.xml";
			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			
			String classFilePath = DisplayController.class.getClassLoader().getResource("com/controller/DisplayController.class").toString();
			
			log.info("class display:" + DisplayController.class.getClassLoader().getResource("com/controller/DisplayController.class"));
			
			//log.info("display:" + DisplayController.class.getClassLoader().getResource("/src/main/resources/ASIAserverDetails.xml").toString());
			
			xmlFileNamePath = extractFilePathWebInf(classFilePath) + "/Resources/" +   xmlFileNamePath +  "serverDetails.xml";

			
			log.info("xmlFileNamePath:" + xmlFileNamePath);
			//xmlFileNamePath = DisplayController.class.getClassLoader().getResource("../resources/ASIAserverDetails.xml").toString().substring(5);
			//xmlFileNamePath ="../resources/ASIAserverDetails.xml";
			
			ServerDetails serverDetails = callxmlReader.xmlReaderServerDetails(xmlFileNamePath);
			servers = serverDetails.getServer();
			servermapper = callxmlReader.mapXMLServerVariables(servers);
			
			return servermapper;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception Received:"+ e.getMessage());
		}
		
		return servermapper;
	}
	
	private String extractFilePathWebInf(String filepath)
	{
		String filepathfinal ="";

		//String filepath = "zip:G:/TEMP/cs/appsrv/domains/mydomain/servers/mg1/tmp/_WL_user/LDODashBoard/kf3gtr/war/WEB-INF/lib/_wl_cls_gen.jar!/com/controller/DisplayController.class";
		
		//find the text string with web-inf
		String filepathWithwebinf = filepath.substring(0,filepath.indexOf("WEB-INF/")+ 7);
		
		log.info("filepathWithwebinf->" + filepathWithwebinf);
		
		if (filepathWithwebinf.substring(0, 3).compareToIgnoreCase("zip") == 0)
		{
			filepathfinal = filepathWithwebinf.substring(4);
		}
		else if(filepathWithwebinf.substring(0, 4).compareToIgnoreCase("file") == 0) 
		{
			filepathfinal = filepathWithwebinf.substring(6);
		}
		else if(filepathWithwebinf.substring(0, 4).compareToIgnoreCase("http") == 0) 
		{
			filepathfinal = filepathWithwebinf.substring(6);		
		}

		System.out.println ("filepathfinal->" + filepathfinal);
		
		return filepathfinal;
	}

}



