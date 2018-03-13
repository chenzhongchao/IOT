package com.fise.iot.common.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.fise.iot.common.Constant;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.service.DeviceLogService;

/**
 * MQTT配置
 */
@Configuration
public class MQTTConfig {
	
	@Value("${mqtt.host}") 
	private String host;
	@Value("${mqtt.publish.topic}") 
	private String publish;
	@Value("${mqtt.subscribe.topic}")
	private String subscribe;
	@Value("${mqtt.serverid}") 
	private String serverid;
	@Value("${mqtt.clientid}") 
	private String clientid;
	@Value("${mqtt.username}") 
	private String username;
	@Value("${mqtt.password}") 
	private String password;
	@Value("${mqtt.connect.timeout}") 
	private String timeout;
	@Value("${mqtt.keep.alive}") 
	private String alive;
	@Value("${mqtt.clean.session}") 
	private String session;
	@Value("${mqtt.qos}") 
	private String qos;
	
	@Autowired
	DeviceLogService deviceLogService;

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs(host);
		factory.setUserName(username);
		factory.setPassword(password);
		return factory;
	}

	@Bean
	public MqttPahoMessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(serverid, mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic(publish);
		return messageHandler;
	}

//	@Bean
//	public MessageChannel mqttOutboundChannel() {
//		return new DirectChannel();
//	}
//
//	@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
//	public interface MyGateway {
//
//		void sendToMqtt(String data);
//
//	}
	
	
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(host,
				clientid, subscribe);
//		adapter.setCompletionTimeout(500000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				if (null != message.getHeaders().get("mqtt_topic") && null != message.getPayload()) {
					String topic = message.getHeaders().get("mqtt_topic").toString();
					String[] topics = topic.replace(subscribe.replace("#", ""),"").split("/");
					DeviceLog deviceLog = new DeviceLog();
					if (topics.length > 1) {
						deviceLog.setProductId(topics[0]);
						deviceLog.setDeviceName(topics[1]);
						deviceLog.setDetail(message.getPayload().toString());
						deviceLog.setType(Constant.TOPIC_TYPE_DOWN);
						deviceLog.setCreateTime(DateUtil.getCurDateTime());
						deviceLogService.insert(deviceLog);
					}
				}
			}

		};
	}
}