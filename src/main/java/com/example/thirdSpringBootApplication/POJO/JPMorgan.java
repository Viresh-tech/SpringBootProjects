package com.example.thirdSpringBootApplication.POJO;

import org.springframework.stereotype.Component;

@Component("jp")
public class JPMorgan implements Bank {

	public String getBankName() {

		return "JP Morgan";
	}

}
