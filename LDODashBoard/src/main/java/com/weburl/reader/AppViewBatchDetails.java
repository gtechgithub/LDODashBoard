package com.weburl.reader;

public class AppViewBatchDetails {

	private String jobName;
	private String description;
	private String oDATE;
	private String startTime;
	private String endTime;
	private String status;
	private String runDuration;
	private String cyclicJob;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getoDATE() {
		return oDATE;
	}
	public void setoDATE(String oDATE) {
		this.oDATE = oDATE;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRunDuration() {
		return runDuration;
	}
	public void setRunDuration(String runDuration) {
		this.runDuration = runDuration;
	}
	public String getCyclicJob() {
		return cyclicJob;
	}
	public void setCyclicJob(String cyclicJob) {
		this.cyclicJob = cyclicJob;
	}

}
