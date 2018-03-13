package mqtt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fise.iot.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMQTT {
	
	@Value("${mqtt.publish.topic}") 
	private String publish;
	@Value("${mqtt.serverid}") 
	private String serverid;

	@Autowired
	MqttPahoMessageHandler messageHandler;
	
	
	
	@Test
	public void publish() throws Exception{
		String topicUrl = "ProductId/DeviceName/";
	    String content = "6666666666";
	    Integer qos = 1;
	    messageHandler.setDefaultTopic(publish + topicUrl);
	    messageHandler.setDefaultQos(qos);
	    Message<String> message = MessageBuilder.withPayload(content).build();  
	    messageHandler.handleMessage(message);  
        System.out.println("成功");  
	}
	
}
