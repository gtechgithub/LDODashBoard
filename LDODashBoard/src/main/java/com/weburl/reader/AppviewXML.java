package com.weburl.reader;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class AppviewXML {
	
	@XmlElement
	private String id;
	
	@XmlElement
	private String urlDescription;

	@XmlElement
	private String urlLink;
	
	@XmlElement
	private List <JobList> jobList;
	
	public String getId() {
		return id;
	}

	public String getUrlDescription() {
		return urlDescription;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public List<JobList> getJobList() {
		return jobList;
	}



}


