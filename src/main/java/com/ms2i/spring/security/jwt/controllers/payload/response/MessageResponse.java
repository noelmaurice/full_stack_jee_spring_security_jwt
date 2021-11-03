package com.ms2i.spring.security.jwt.controllers.payload.response;

/**
 * 
 * Http response structure, attributes and accessors for a response message
 * 
 * @author NOEL MAURICE
 *
 */
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
