package com.fise.iot.common.pojo;

import java.util.List;

import com.fise.iot.model.AuthOperation;
import com.fise.iot.model.AuthUser;

/**
 * 封装Session
 */
public class Identity {
	private String sessionId;
	private String loginIp;
	private AuthUser loginUser;
	private List<AuthOperation> operationList;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public AuthUser getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(AuthUser loginUser) {
		this.loginUser = loginUser;
	}
	public List<AuthOperation> getOperationList() {
		return operationList;
	}
	public void setOperationList(List<AuthOperation> operationList) {
		this.operationList = operationList;
	}
	
}
