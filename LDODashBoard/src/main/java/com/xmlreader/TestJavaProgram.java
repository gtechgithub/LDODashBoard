package com.xmlreader;

public class TestJavaProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String filepath = "zip:G:/TEMP/cs/appsrv/domains/mydomain/servers/mg1/tmp/_WL_user/LDODashBoard/kf3gtr/war/WEB-INF/lib/_wl_cls_gen.jar!/com/controller/DisplayController.class";
		
		String filepath = "file:/M:/Learning/apache-tomcat-7.0.63/webapps/LDODashBoard/WEB-INF/classes/com/controller/DisplayController.class";
		
		//find the text string with web-inf
		String filepathWithwebinf = filepath.substring(0,filepath.indexOf("WEB-INF/")+ 7);
		
		System.out.println ("filepathWithwebinf->" + filepathWithwebinf);
		
		//remove the text string containing file/zip/http etc
		
		String filepathfinal = "";
	
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
	}

}
