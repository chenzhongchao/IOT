package com.fise.iot.model;

import java.io.Serializable;

public class TopicSave implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    private String prefix;
    private String suffix;
    private Integer operAuth;
    private String topicDesc;
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getOperAuth() {
		return operAuth;
	}
	public void setOperAuth(Integer operAuth) {
		this.operAuth = operAuth;
	}
	public String getTopicDesc() {
		return topicDesc;
	}
	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}
	@Override
	public String toString() {
		return "TopicSave [prefix=" + prefix + ", suffix=" + suffix + ", operAuth=" + operAuth + ", topicDesc="
				+ topicDesc + "]";
	}
}
