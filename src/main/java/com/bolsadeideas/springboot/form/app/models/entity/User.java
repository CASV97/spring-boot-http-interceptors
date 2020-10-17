package com.bolsadeideas.springboot.form.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.validation.Validation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.form.app.validation.IdentifierRegex;
import com.bolsadeideas.springboot.form.app.validation.Required;

/** Clase User Ejemplo de validaciones con {@link Validation} */
public class User {

	// ejemplo de clase de validación
	@IdentifierRegex
	@Required
	private String identifier;

	@Required
	@Size(min = 4, max = 8)
	private String username;

	@Required
	@Size(min = 4)
	private String password;

	@Required
	@Email
	private String email;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer account;

	@NotNull
	@PastOrPresent
	private Date birthdate;
	// con @Valid pedimos que se validen los objetos relacionados

	@NotNull
	private Country country;

	@NotEmpty
	private List<Role> roles;

	private Boolean enable;

	@NotEmpty
	private String gender;

	private String secretValue;

	public User() {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}
}
