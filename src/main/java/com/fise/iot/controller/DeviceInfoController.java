package com.fise.iot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.Constant;
import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.model.Device;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.model.MQTTDto;
import com.fise.iot.model.Topic;
import com.fise.iot.service.DeviceInfoService;
import com.fise.iot.service.DeviceLogService;
import com.fise.iot.service.ProductInfoService;
import com.fise.iot.service.TopicService;

/**
 * 设备基本信息controller
 */
@Controller
@RequestMapping("/admin/device/")
public class DeviceInfoController {
	/** 日志 */
	public static Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);


	@Value("${mqtt.publish.topic}") 
	private String publish;
	
	@Autowired
	MqttPahoMessageHandler messageHandler;
	
	@Autowired
	private DeviceInfoService deviceService;
	
	@Autowired
	private DeviceLogService deviceLogService;

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private ProductInfoService productService;

	@Authority(opCode = "0402", opName = "设备基本信息界面")
	@RequestMapping("deviceinfoPage/{productId}")
	public String devicePage(@PathVariable("productId") String productId, Map<String, Object> map) {
		map.put("productId", productId);
		return "device/device_info";
	}
	/**
	 * 传入的是产品的id
	 */
	@ControllerLog("查询设备列表")
	@RequestMapping("queryDevicePage/{productId}")
	@ResponseBody
	@Authority(opCode = "0402", opName = "查询设备列表")
	public PageAjax<Device> queryDevicePage(@PathVariable("productId") String productId,PageAjax<Device> page, Device device) {
//		Product product = productService.queryByID(id);
//		String productId = product.getProductId();
		return deviceService.queryDevicePage(page, device,productId);
	}

	@Authority(opCode = "040201", opName = "更新设备页面")
	@RequestMapping("updateDevicePage/{id}")
	public String updateDevicePage(@PathVariable("id") int id, Map<String, Object> map) {
		Device device = deviceService.queryDeviceByID(id);
		map.put("device", device);
		return "device/device_update";
	}

	@ControllerLog("修改设备")
	@RequestMapping("updateDevice")
	@ResponseBody
	@Authority(opCode = "040201", opName = "修改设备")
	public AjaxResult updateDevice(Device device) {
		return deviceService.updateDevice(device);
	}

	@Authority(opCode = "040202", opName = "Topic列表页面")
	@RequestMapping("topicListPage/{productId}/{deviceName}")
	public String topicListPage(@PathVariable("deviceName") String deviceName,
			                    @PathVariable("productId") String productId,
			                    Map<String, Object> map) {
		map.put("deviceName", deviceName);
		map.put("productId", productId);
		return "device/topic_list";
	}

	@ControllerLog("Topic列表页面")
	@RequestMapping("topicResultPage/{productId}/{deviceName}")
	@ResponseBody
	@Authority(opCode = "040202", opName = "Topic列表页面")
	public PageAjax<Topic> topicResultPage(@PathVariable("productId") String productId,
			                               @PathVariable("deviceName") String deviceName) {
//		// 根据id查出该设备的信息
//		Device device = deviceService.queryDeviceByID(id);
//		String deviceName = device.getDeviceName();
		// 根据其中的productId查出所有的Topic，替换其中的devicename
		//String productId = device.getProductId();
		List<Topic> topicList = topicService.queryTopicPage(productId, deviceName);
		
		return new PageAjax<Topic>(topicList);
		
	}

	@ControllerLog("删除设备")
	@RequestMapping("delDevice/{id}")
	@ResponseBody
	@Authority(opCode = "040203", opName = "删除设备")
	public AjaxResult delDevice(@PathVariable("id") int id) {
		return deviceService.delDevice(id);
	}

	@Authority(opCode = "040204", opName = "添加设备页面")
	@RequestMapping("addDevicePage/{productId}")
	public String addDevicePage(@PathVariable("productId") String productId, Map<String, Object> map) {
		map.put("productId", productId);
		return "device/device_add";
	}

	@ControllerLog("添加设备")
	@RequestMapping("addDevice")
	@ResponseBody
	@Authority(opCode = "040204", opName = "添加设备")
	public AjaxResult addDevice(Device device) {
		return deviceService.addDevice(device);
	}
	
	/**
	 * 传入的是topic的id
	 */
	@Authority(opCode = "040205", opName = "发布消息页面")
	@RequestMapping("devicePublishPage/{id}")
	public String devicePublishPage(@PathVariable("id") int id,String topicUrl, Map<String, Object> map) {
//		根据topicId查出对应得topicUrl
//		Topic topic=topicService.queryTopicByID(id);
//		String topicUrl=topic.getTopicUrl();
//		String topicUrl=request.getParameter("topicUrl");
		map.put("topicUrl", topicUrl);
		return "device/device_publish";
	}
	
	//需要知道，哪个设备的哪个Topic被操作了。
//	@ControllerLog("发布消息")
//	@RequestMapping("devicePublish")
//	@ResponseBody
//	@Authority(opCode = "040205", opName = "发布消息")
//	public AjaxResult devicePublish(MessagePublish message) {
//		topicService.publishMessage(message);
//		return null;
//	}
	
	@ControllerLog("发布消息")
	@Authority(opCode = "040205", opName = "发布消息")
	@ResponseBody
	@RequestMapping("devicePublish/{id}")
	public AjaxResult publishMsg(@PathVariable("id") int id,MQTTDto mqtt){
	    String topicUrl = mqtt.getTopicUrl();
	    String content = mqtt.getContent();
	    Integer qos = mqtt.getQos();
	    String [] str= topicUrl.split("/");
	    try {
		    messageHandler.setDefaultTopic(publish + topicUrl);
		    messageHandler.setDefaultQos(qos);
		    Message<String> message = MessageBuilder.withPayload(content).build();  
		    messageHandler.handleMessage(message);  
		    
	    	DeviceLog deviceLog = new DeviceLog();
	    	String productKey=str[1];
	        String productId=productService.getProductIdByKey(productKey);
	        if(StringUtil.isEmpty(productId)) {
	        	return AppUtil.returnObj("获取产品ID失败");
	        }
	    	deviceLog.setProductId(productId);
	    	deviceLog.setDeviceName(str[2]);
	    	deviceLog.setDetail(content);
	    	deviceLog.setType(Constant.TOPIC_TYPE_UP);
	    	deviceLog.setCreateTime(DateUtil.getCurDateTime());
	    	deviceLogService.save(deviceLog);
	    	
		} catch (Exception e) {
			return AppUtil.returnObj("操作失败");
		}
	    //发布消息的数量要累加
	    Topic topic=  topicService.queryByID(id);
	    topic.setMessageNum(topic.getMessageNum()+1);
	    topic.setUpdateTime(DateUtil.getCurDateTime());
	    topicService.update(topic);
	    return AppUtil.returnObj(null);
	}
	
}
