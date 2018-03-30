package com.unixconnection;

import java.util.List;

public class UnixProcessOutput {

	private List <StringBuffer> output;
	private String description;

	public List<StringBuffer> getOutput() {
		return output;
	}
	public void setOutput(List<StringBuffer> output) {
		this.output = output;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
