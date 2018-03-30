package com.weburl.reader;

import javax.xml.bind.annotation.XmlElement;

public class JobList {
	
	@XmlElement
	private String jobName;

	@XmlElement
	private String briefDescription;

	@XmlElement
	private String comments;

	public String getJobName() {
		return jobName;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public String getComments() {
		return comments;
	}
}
