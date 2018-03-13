package com.fise.iot.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.service.DeviceLogService;

@Controller
@RequestMapping("/admin/log/")
public class DeviceLogController {
	
	/** 日志 */
	public static Logger logger = LoggerFactory.getLogger(DeviceLogController.class);

	@Autowired
	private DeviceLogService logService;

	
	@Authority(opCode = "0405", opName = "设备日志管理界面")
	@RequestMapping("deviceLogPage/{productId}")
	public String deviceLogPage(@PathVariable("productId") String productId, Map<String, Object> map){
		map.put("productId", productId);
		return "log/device_log";
	}
	
	@ControllerLog("查询设备日志")
	@RequestMapping("queryDeviceLogPage/{productId}")
	@ResponseBody
	@Authority(opCode = "0405", opName = "查询设备日志")
	public PageAjax<DeviceLog> queryDeviceLogPage(PageAjax<DeviceLog> page, DeviceLog deviceLog,String time) {
		//time  0-一天内，1-一个星期内，2-一个月内
		return logService.queryDeviceLogPage(page, deviceLog,time);
	}
}
