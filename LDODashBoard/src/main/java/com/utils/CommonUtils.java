package com.utils;

import org.apache.log4j.Logger;

import com.xmlreader.ExecuteUnixXMLReader;

public class CommonUtils {
	
	static Logger log = Logger.getLogger(CommonUtils.class.getName());

	public static String extractFilePathWebInf(String filepath)
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
