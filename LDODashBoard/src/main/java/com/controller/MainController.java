package com.controller;

import com.common.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.security.UrlAuthenticationSuccessHandler;
import com.unixconnection.SshClient;
import com.unixconnection.UnixProcessOutput;
import com.unixconnection.Unixprocessdetail;
import com.unixconnection.Unixprocessdetails;
import com.xmlreader.ExecuteUnixXMLReader;
import com.dao.AuditDisplayPostClass;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class MainController {

	static Logger log = Logger.getLogger(MainController.class.getName());

	@RequestMapping("/showMessage")
	public ModelAndView helloWorld() {

		System.out.println("inisde showmessage method");
		log.info("inisde showmessage method");

		String message = "L1 SUPPORT LANDING PAGE";
		return new ModelAndView(CommonConstants.L1DashboardPage, "message", message);
		//return new ModelAndView(CommonConstants.OutputDisplayPage, "message", message);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "error", required = false) String error) {

		System.out.println("inisde welcome method");
		log.info("inisde welcome method");

		ModelAndView model = new ModelAndView();

		if (error != null) {
			log.info("inisde error not null ");

			model.addObject("error", "Invalid username and password!");
		}

		model.setViewName(CommonConstants.loginPage);

		return model;
	}

	@RequestMapping(value = "/fail2LoginPage", method = RequestMethod.GET)
	public ModelAndView loginerror() {
		System.out.println("inisde fail2login method");
		return new ModelAndView(CommonConstants.Fail2LoginPage);
	}

	@RequestMapping(value = "/logoutPage")
	public String logout(HttpSession session) {

		System.out.println("inisde logout method");
		// session.invalidate();
		UrlAuthenticationSuccessHandler url = new UrlAuthenticationSuccessHandler();
		url.onLogout(session);
		return CommonConstants.LogoutPage;

	}

	@RequestMapping(value = "/L1SrcLinkPage")
	public String l1srcLinkMethod(HttpSession session) {

		log.info("inside l1srcLinkMethod method");
		// session.invalidate();
		return CommonConstants.L1SrcLinkPage;

	}

	@RequestMapping(value = "/L2SrcLinkPage")
	public String l2srcLinkMethod(HttpSession session) {

		log.info("inside l2srcLinkMethod method");
		// session.invalidate();
		return CommonConstants.L2SrcLinkPage;

	}
	

	@RequestMapping(value = "/L2LandingPage", method = RequestMethod.GET)
	public ModelAndView L2LandingPageMethod() {

		System.out.println("inisde L2LandingPageMethod method");
		String message = "<div style='text-align:center;'>"
				+ "<h3> L2 LDO Support Landing page </h3> </div>";
		return new ModelAndView(CommonConstants.L2LandingPage, "message", message);
	}
	
	@RequestMapping(value = "/ldmUnprocessedLink", method = RequestMethod.GET)
	public String ldmUnprocessedMethod(Model model, HttpServletResponse response) {

		log.info("inisde ldmUnprocessedMethod method");
		return "";
	}

	@RequestMapping(value = "/gcmmLink2", method = RequestMethod.GET)
	public String gcmmLink2() {

		log.info("inisde gcmmLink2 method");
		return "";
	}

	@RequestMapping(value = "/dbCheckout", method = RequestMethod.GET)
	public String dbCheckoutMethod(Model model, HttpServletResponse response) {

		log.info("inisde dbCheckoutMethod method");
		return "";
	}
	
	
	
	@RequestMapping(value = "monitorUnixProcess", method = RequestMethod.GET)
	public ModelAndView monitorUnixProcess(
			@RequestParam(value = "processName", required = false) String processName,
			HttpSession session,HttpServletResponse response) {
		
		log.info("inisde monitorUnixProcess method");
		
		try {
			
	        ExecuteUnixXMLReader xmlReader = new ExecuteUnixXMLReader();
	        xmlReader.populateFromXML();
	        SshClient sshClient = new SshClient();
	        Unixprocessdetail processDetail =  null;
	        

	        if (! processName.isEmpty()){
				if (processName.equals("COMFEE04D")) {
					
					log.info("inisde COMFEE04D method");
			        processDetail = xmlReader.getAppropriateUnixDetail("COMFEE04D");

				}
				else if (processName.equals("GCMLANDINGCHECHKOUT")) {
					
					log.info("inisde GCMLANDINGCHECHKOUT method");
					processDetail = xmlReader.getAppropriateUnixDetail("GCMLANDINGCHECHKOUT");
				}
				
		        UnixProcessOutput output = sshClient.ExecuteCommandWrapper(processDetail.getUserName(),processDetail.getPassword(),
                        processDetail.getHostName(),processDetail.getCommands(),
                        processDetail.getJobDescription());
		        
				//return the dummy outputdisplay page, show nothing
				return (new ModelAndView(CommonConstants.UnixProcessDisplayPage,"unixprocessoutput",output));

	        }
		}
		catch (Exception e){
	    	   log.error("exception received here!!!!");
	    }
	  return (new ModelAndView(CommonConstants.OutputDisplayPage,"",""));
	}



}
