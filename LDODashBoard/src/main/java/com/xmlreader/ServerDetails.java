package com.xmlreader;

import com.xmlreader.Server;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.List;
import java.util.ArrayList;


@XmlRootElement(name="ServerDetails")
public class ServerDetails {
	
	@XmlElementWrapper(name = "Servers")
	@XmlElement(name = "Server")
	private List <Server> server = new ArrayList<Server>();

	
	
	public List<Server> getServer() {
		return server;
	}


	


}
