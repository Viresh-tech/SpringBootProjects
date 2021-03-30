package com.example.thirdSpringBootApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	private static final String TOPIC = "topic_one";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessages(String message) {
		kafkaTemplate.send(TOPIC, message);
	}

}
