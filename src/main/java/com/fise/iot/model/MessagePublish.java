package com.fise.iot.model;

public class MessagePublish {
	
	private String topicUrl;
	
	private String messageContent;
	
	private int qos;

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	@Override
	public String toString() {
		return "MessagePublish [topicUrl=" + topicUrl + ", messageContent=" + messageContent + ", qos=" + qos + "]";
	}
	
}
