package com.example.thirdSpringBootApplication.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics = "topic_one", groupId = "group_id")
	public void consumeMessages(String messages) {
		System.out.println(messages);
	}

}
