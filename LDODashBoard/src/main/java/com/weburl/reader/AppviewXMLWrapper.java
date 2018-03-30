package com.weburl.reader;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Appviewsdetails")
public class AppviewXMLWrapper {

	@XmlElementWrapper(name = "Appviewdetail")
	@XmlElement(name = "Appview")
	private List <AppviewXML> appview;

	public List<AppviewXML> getAppview() {
		return appview;
	}

}
