package com.xmlreader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


public class Server {

	@XmlElement
	private String serverName;

	@XmlElement
	private String serverDescription;
	
    @XmlElement
	private String  serverSharedPath;

	@XmlElement
    private String serverUserId;
    
	@XmlElement
    private String serverUserPassword;
	
	@XmlElement(name = "DRServer")
	private DRServer DRServerDetails;
	
    public String getServerDescription() {
		return serverDescription;
	}
    
    public String getServerName() {
		return serverName;
	}

	public String getServerSharedPath() {
		return serverSharedPath;
	}

	public String getServerUserId() {
		return serverUserId;
	}

	public String getServerUserPassword() {
		return serverUserPassword;
	}
	
	public DRServer getDRServerDetails() {
		return DRServerDetails;
	}
	
	/*
	public void setDRServerDetails(DRServer DRServerDetails ) {
		this.DRServerDetails = DRServerDetails;
	}
	*/
}

class DRServer {
	@XmlElement
	private String DRserverName;

	@XmlElement
	private String  DRserverSharedPath;

    public String getDRServerName() {
		return DRserverName;
	}

	public String getDRServerSharedPath() {
		return DRserverSharedPath;
	}

    public void setDRServerName(String serverName) {
		this.DRserverName = DRserverName;
	}

	public void setDRServerSharedPath(String serverSharedPath ) {
		this.DRserverSharedPath = DRserverSharedPath;
	}

}
