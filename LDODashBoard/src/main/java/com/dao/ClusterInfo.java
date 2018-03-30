package com.dao;

import java.sql.Timestamp;

public class ClusterInfo {
	
	private String product;
	private String ip_address;
	private Integer http_port;
	private Integer rmi_port;
	private String node_type;
	private String host_name;
	private String status;
	
	private String laastseen;
	
	public String getLaastseen() {
		return laastseen;
	}
	public void setLaastseen(String laastseen) {
		this.laastseen = laastseen;
	}

	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public Integer getHttp_port() {
		return http_port;
	}
	public void setHttp_port(Integer http_port) {
		this.http_port = http_port;
	}
	public Integer getRmi_port() {
		return rmi_port;
	}
	public void setRmi_port(Integer rmi_port) {
		this.rmi_port = rmi_port;
	}
	public String getNode_type() {
		return node_type;
	}
	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/***
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	***/

}
