package com.rewards.calculator.model;

public class ResponseEntity {
	
	public ResponseEntity(int status, String message, Object response) {
		this.status = status;
		this.message = message;
		this.response = response;
	}
	
	int status;
	
	Object response;
	
	String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
