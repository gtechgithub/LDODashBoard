package com.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.implementation.LnkConnectionCheck;


public class LnkConnectionWrapper {

	public List <LnkConnectionCheck> lnkConnectionCheck;
	static Logger log = Logger.getLogger(LnkConnectionWrapper.class.getName());

	public List getLnkConnectionCheck() {
		return lnkConnectionCheck;
	}

	public void setLnkConnectionCheck(List <LnkConnectionCheck> lnkConnectionCheck) {

		log.info("inside the setLnkConnectionCheck method");
		//lnkConnectionCheck = new ArrayList <LnkConnectionCheck> ();
		this.lnkConnectionCheck = lnkConnectionCheck;
		
		log.info("Count of entries in the lnkConnectionCheck list:" + lnkConnectionCheck.size());
	}
}
