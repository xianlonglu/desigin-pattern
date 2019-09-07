package com.lxl.kafkaspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 */

@Component
public class GpKafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String key, String value) {
		kafkaTemplate.send(topic, key, value);
	}

}
