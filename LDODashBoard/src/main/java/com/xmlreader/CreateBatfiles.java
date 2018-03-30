package com.xmlreader;


import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.xmlreader.Server;
import com.xmlreader.ServerDetails;
import com.xmlreader.ExecuteXMLReader;



public class CreateBatfiles {

	private String configFilePath;
	private String configFileName;
	private String batFileCreatePath;
	private List <Server> servers;
	private List <ServerMapper> serverMapper;

	static Logger log = Logger.getLogger(ExecuteXMLReader.class.getName());


	public CreateBatfiles() {
	
		log.info("Ïnside Constructor");
		//path of the ASIA/AMERICA/EMEA server details XML file
		configFilePath = "src/main/webapp/WEB-INF/resources/";
		
		//path where the bat file is created
		//batFileCreatePath = "M:\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\src\\main\\webapp\\WEB-INF\\resources\\Temp\\";
		batFileCreatePath = "M:\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\src\\main\\webapp\\WEB-INF\\resources\\";
	}

	public void getServerinformations(final String  xmlFileName)
	{
		try {

			//read the ASIA/AMERICA/EMEA server details XML file
			configFileName = xmlFileName;
			ExecuteXMLReader xmlReader = new ExecuteXMLReader();
			
			log.info("configFilePath:" + configFilePath);
			log.info("configFileName:" + configFileName);
			
			ServerDetails serverDetails =  xmlReader.xmlReaderServerDetails(configFilePath + configFileName);
			
			servers = serverDetails.getServer();
			
			//map the server list to the servermapper for easier access
			serverMapper = xmlReader.mapXMLServerVariables(servers);

		}
		catch(Exception e){
			log.info("Exception received inside getServerinformations");
			e.printStackTrace();
		}
	}

	public StringBuffer contenttoWrite(final String serverSharedPath)
	{
		String tempString = "";
		StringBuffer buffer = new StringBuffer();
		
		tempString = "@echo off \n";
		buffer.append(tempString);
		
		tempString = "explorer " + serverSharedPath + "\n";
		buffer.append(tempString);
		
		return buffer;
	}
	
	public void executeBatFileCreator(final String serverName, final String serverSharedPath)
	{
		
		try
		{
			String completeFilepath = batFileCreatePath + "ViewShared" + serverName + ".bat";
			log.info("Creating the content:"+ serverSharedPath + " for ServerFile:" + serverName);
			
			File file = new File(completeFilepath);
			PrintWriter printWriter = new PrintWriter(file,"UTF-8");
			
			BufferedWriter writer = new BufferedWriter(printWriter);
			
			//this return the StringBuffer which contains the content of the file
			StringBuffer sWriteContent = contenttoWrite(serverSharedPath);
			writer.write(sWriteContent.toString());
			
			writer.close();
			printWriter.close();
		}
		catch(Exception e)
		{
			System.out.println("Catching Exception have a look!!!!");
			e.printStackTrace();
		}
	}
	
	public void executeBatFileCreatorWrapper()
	{
		for (ServerMapper serverList: serverMapper ){
			executeBatFileCreator(serverList.getServerName(),serverList.getServerSharedPath());
			executeBatFileCreator(serverList.getDRserverName(),serverList.getDRserverSharedPath());
		}
	}
	
	public static void main(String args[])
	{
		try{
			CreateBatfiles batFileExe = new CreateBatfiles();
			
			//need to change the XML file here accordingly.
			/*
			batFileExe.getServerinformations("ASIAserverDetails.xml");
			batFileExe.executeBatFileCreatorWrapper();
			
			batFileExe.getServerinformations("EMEAserverDetails.xml");
			batFileExe.executeBatFileCreatorWrapper();
			
			batFileExe.getServerinformations("AMERICAserverDetails.xml");
			batFileExe.executeBatFileCreatorWrapper();
			*/
			
			batFileExe.getServerinformations("MatchDerivativeserverDetails.xml");
			batFileExe.executeBatFileCreatorWrapper();

			log.info(" Batch file creation completèd!!!");
		}
		catch(Exception e){
			log.info("Exception received inside main");
			e.printStackTrace();
		}
		
	}
	
}
