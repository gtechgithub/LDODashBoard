package com.xmlreader;

import javax.xml.bind.annotation.XmlElement;

public class ServerMapper {
	private String serverName;

	private String serverDescription;
	
	private String  serverSharedPath;

    private String serverUserId;
    
	private String DRserverName;

	private String  DRserverSharedPath;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerDescription() {
		return serverDescription;
	}

	public void setServerDescription(String serverDescription) {
		this.serverDescription = serverDescription;
	}

	public String getServerSharedPath() {
		return serverSharedPath;
	}

	public void setServerSharedPath(String serverSharedPath) {
		this.serverSharedPath = serverSharedPath;
	}

	public String getDRserverName() {
		return DRserverName;
	}

	public void setDRserverName(String dRserverName) {
		DRserverName = dRserverName;
	}

	public String getDRserverSharedPath() {
		return DRserverSharedPath;
	}

	public void setDRserverSharedPath(String dRserverSharedPath) {
		DRserverSharedPath = dRserverSharedPath;
	}

}
