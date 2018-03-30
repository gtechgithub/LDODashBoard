package com.unixconnection;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

public class Unixprocessdetail {

	@XmlElement
	private String jobname;

	@XmlElement
	private String username;

	@XmlElement
	private String password;

	@XmlElement
	private String hostname;

	@XmlElement
	private String jobdescription;

	@XmlElement(name = "commands")
	private Command commands;

	
	public String getJobName() {
		return jobname;
	}

	public void setJobName(String jobName) {
		this.jobname = jobName;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public String getHostName() {
		return hostname;
	}

	public void setHostName(String hostName) {
		this.hostname = hostName;
	}

	public String getJobDescription() {
		return jobdescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobdescription = jobDescription;
	}

	public Command getCommands() {
		return commands;
	}

}

class Command {

	@XmlElement
	private List <String> command;

	public List <String> getCommand() {
		return command;
	}

}
