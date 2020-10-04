package com.bolsadeideas.springboot.form.app.models.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.form.app.validation.IdentifierRegex;
import com.bolsadeideas.springboot.form.app.validation.Required;

public class User {
	// ejemplo de clase de validación
	@IdentifierRegex
	@Required
	private String identifier;
	@Required
	@Size(min = 4, max = 10)
	private String username;
	@Required
	@Size(min = 8, message = "Debe tener 8 carácteres como mínimo")
	private String password;
	@Required
	@Email
	private String email;
	@NotNull
	@Min(5)
	@Max(5000)
	private Integer account;

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

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}
}
