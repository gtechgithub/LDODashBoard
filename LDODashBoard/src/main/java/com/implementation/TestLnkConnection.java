package com.implementation;

import java.util.ArrayList;
import java.util.List;

import com.common.CommonConstants;
import com.controller.UrlConnectionController;
import com.implementation.LnkConnectionEngine;
import com.implementation.LnkConnectionResponse;
import com.implementation.LnkConnectionWrapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class TestLnkConnection {

	private LnkConnectionWrapper linkConnectionArray;

	static Logger log = Logger.getLogger(TestLnkConnection.class.getName());	

	public void testConnection() {
		try{
			
			List <LnkConnectionResponse> connResponse = new ArrayList <LnkConnectionResponse> ();
	
		  	//ApplicationContext appContext = new  XmlApplicationContext("");
			//Resource xmlResource = new FileSystemResource("\\NSNG11P20134A\\gcothak2$\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\src\\main\\webapp\\WEB-INF\\mvc-database.xml");
			//Resource xmlResource = new FileSystemResource("/src/main/webapp/WEB-INF/mvc-database.xml");
			
			log.info ("before xml resource");
			//BeanFactory factory = new XmlBeanFactory(xmlResource));
			
			ApplicationContext context = new FileSystemXmlApplicationContext("M:\\mvc-database.xml");
			
			log.info ("before get bean");
			LnkConnectionWrapper linkConnectionArray = (LnkConnectionWrapper)context.getBean("linkConnectionArray");
	
			
			log.info("Count of the linkConnectionArray:"+ linkConnectionArray.getLnkConnectionCheck().size());
			
			//call the lnk connection engine and return the output of logs
			connResponse = (new LnkConnectionEngine()).processforLnkConnectionGroup(linkConnectionArray.getLnkConnectionCheck(),CommonConstants.CDRLinkGroup);
			
			log.info("connResponse UrlDescription:" + connResponse.get(0).getUrlDescription());
			log.info("connResponse UrlResponseDescription:" + connResponse.get(0).getUrlResponseDescription());
			log.info("connResponse UrlResponseStatus:" + connResponse.get(0).getUrlResponseStatus());
			

			
		}
		catch(Exception e){
	    	log.error("Exception Received here!!!");
	        e.printStackTrace();
		}
	}
	
	public static void main (String args[]){
		TestLnkConnection tst  =  new TestLnkConnection();
		tst.testConnection();
	}
}
