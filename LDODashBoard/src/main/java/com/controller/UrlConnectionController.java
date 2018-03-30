package com.controller;

import com.common.CommonConstants;
import com.implementation.LnkConnectionEngine;
import com.implementation.LnkConnectionResponse;
import com.implementation.LnkConnectionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UrlConnectionController {

	@Autowired
	private LnkConnectionWrapper linkConnectionArray;
	
	static Logger log = Logger.getLogger(UrlConnectionController.class.getName());	
	
	@RequestMapping(value = "lnkConnectionGroupCheck", method = RequestMethod.GET)
	public ModelAndView resolveLnkConnectionGrp(
			@RequestParam(value = "lnkConnectionGroup", required = false) String lnkConnectionGroup){

		if (!lnkConnectionGroup.isEmpty() ){

			//this group check for the cdr GAT and cdr commission manager
			//This group hard code is not needed, but if we don't include and did not do mapping unneccesary issue/error/exception  will  occur.

			if (lnkConnectionGroup.equals(CommonConstants.CDRLinkGroup) || 
					lnkConnectionGroup.equals(CommonConstants.TLMLinkGroup) || 
					lnkConnectionGroup.equals(CommonConstants.GCMMLinkGroup) ||
					lnkConnectionGroup.equals(CommonConstants.MATCHLinkGroup) ){
				List <LnkConnectionResponse> connResponse = new ArrayList <LnkConnectionResponse> ();

				log.info("Count of the linkConnectionArray:"+ linkConnectionArray.getLnkConnectionCheck().size());
				
				//call the lnk connection engine and return the output of logs
				connResponse = (new LnkConnectionEngine()).processforLnkConnectionGroup(linkConnectionArray.getLnkConnectionCheck(),lnkConnectionGroup);

				//pass the response object to display in the view
				return (new ModelAndView(CommonConstants.UrlConnectivityDisplayPage,"LnkConnectionResponse",connResponse));
			}
		}
		
		//return the dummy outputdisplay page, show nothing
		return (new ModelAndView(CommonConstants.OutputDisplayPage,"Message",""));

	}
	
}
