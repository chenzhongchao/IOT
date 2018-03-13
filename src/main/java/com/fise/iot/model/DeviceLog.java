package com.fise.iot.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 
 */
public class DeviceLog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品Id
     */
    private String productId;

    /**
     * 消息Id
     */
    private String messageId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 消息类型(1上行;2下行)
     */
    private Integer type;

    /**
     * 内容
     */
    private String detail;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 日期
     */
    private String createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "DeviceLog [id=" + id + ", productId=" + productId + ", messageId=" + messageId + ", deviceName="
				+ deviceName + ", type=" + type + ", detail=" + detail + ", statusDesc=" + statusDesc + ", createTime="
				+ createTime + "]";
	}
}