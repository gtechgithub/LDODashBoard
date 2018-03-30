package com.unixconnection;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name="unixdetails")
public class Unixprocessdetails {

	@XmlElementWrapper(name = "unixprocessdetails")
	@XmlElement(name = "unixprocessdetail")
	private List<Unixprocessdetail> unixprocessdetailList;

	public List<Unixprocessdetail> getUnixProcessExecutors() {
		return unixprocessdetailList;
	}
}
