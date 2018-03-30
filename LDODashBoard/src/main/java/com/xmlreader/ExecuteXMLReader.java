package com.xmlreader;

import com.xmlreader.ServerDetails;
import com.xmlreader.Server;
import java.io.File;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class ExecuteXMLReader {

	private ServerDetails serverDetails = null;
	
	static Logger log = Logger.getLogger(ExecuteXMLReader.class.getName());
	public static void main (String args[]) throws Exception
	{
		JAXBContext jc = JAXBContext.newInstance(ServerDetails.class);
		
		//File xml = new File("C:\\Users\\gopalakrishnan\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\src\\main\\java\\com\\xmlreader\\input.xml");
		File xml = new File("src/main/resources/" + "ASIA" +  "serverDetails.xml");
		//File xml = new File("src/main/webapp/WEB-INF/resources" + "ASIA" +  "serverDetails.xml");
		
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		ServerDetails adaptedWrapper = (ServerDetails) unmarshaller.unmarshal(xml);

		for(Server server : adaptedWrapper.getServer()) {
            System.out.println("server description:"+ server.getServerDescription());
            System.out.println("server name:" + server.getServerName());
            System.out.println("DR server name:" + server.getDRServerDetails().getDRServerName());
            System.out.println("DR server shared path:" + server.getDRServerDetails().getDRServerSharedPath());
        }
	}
	
	
	public ServerDetails xmlReaderServerDetails(String xmlFileNamePath) throws Exception
	{
		JAXBContext jc = JAXBContext.newInstance(ServerDetails.class);
		
		log.info("xmlFileNamePath:" + xmlFileNamePath);

		//File xmlFile = new File("C:\\Users\\gopalakrishnan\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\src\\main\\resources\\ASIAserverDetails.xml");
		File xmlFile = new File(xmlFileNamePath);
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		serverDetails = (ServerDetails) unmarshaller.unmarshal(xmlFile);
		
		return serverDetails;
	}
	
	public List <ServerMapper> mapXMLServerVariables(List <Server> server)
	{
		List <ServerMapper> servermapperList = null;
		
		try {
			servermapperList = new ArrayList <ServerMapper> ();
			
			for ( Server serverList : server)
			{
				ServerMapper servermapper = new ServerMapper();
				if (!serverList.getServerName().isEmpty())
				{
					servermapper.setServerName(serverList.getServerName());
				}
				
				if (!serverList.getServerDescription().isEmpty())
				{
					servermapper.setServerDescription(serverList.getServerDescription());
				}
				
				if (!serverList.getServerSharedPath().isEmpty())
				{
					servermapper.setServerSharedPath(serverList.getServerSharedPath());
				}
				
				if (!serverList.getDRServerDetails().getDRServerName().isEmpty())
				{
					servermapper.setDRserverName(serverList.getDRServerDetails().getDRServerName());
				}
				
				if (!serverList.getDRServerDetails().getDRServerSharedPath().isEmpty())
				{
					servermapper.setDRserverSharedPath(serverList.getDRServerDetails().getDRServerSharedPath());
				}
				
				servermapperList.add(servermapper);
			}
			return servermapperList;

		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		return servermapperList;
	}
}
