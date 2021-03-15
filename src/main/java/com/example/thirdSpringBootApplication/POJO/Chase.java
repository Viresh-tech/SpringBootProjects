package com.example.thirdSpringBootApplication.POJO;

import org.springframework.stereotype.Component;

@Component
public class Chase implements Bank {

	public String getBankName() {

		return "Chase";
	}

}
