package com.bolsadeideas.springboot.form.app.models.entity;

import javax.validation.constraints.NotEmpty;

public class User {
	private String identifier;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
