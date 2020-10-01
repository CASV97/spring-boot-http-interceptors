package com.bolsadeideas.springboot.form.app.models.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
	private String identifier;

	@NotEmpty
	@Size(min = 4, max = 10)
	private String username;
	@NotEmpty
	@Size(min = 8, message = "Debe tener 8 carácteres como mínimo")
	private String password;
	@NotEmpty
	@Email
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
