package com.cts.fsebkend.apigatewayserver.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("password")
	private String password;

	//need default constructor for JSON Parsing
	public JwtRequest(){

	}

	public JwtRequest(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [userName=" + userName + ", password=" + password + "]";
	}
}
