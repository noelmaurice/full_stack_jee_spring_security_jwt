package com.ms2i.spring.security.jwt.controllers.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * 
 * Http response structure, attributes and accessors for a JWT object, 
 * with attributes and accessors
 * 
 * @author NOEL MAURICE
 *
 */
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
