package com.example.thirdSpringBootApplication.POJO;

public class AuthenticationResponse {
	
	private final String jwt;

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
	
	
	

}
