package com.bolsadeideas.springboot.form.app.models.entity;

// Llenando checkboxes con objetos del tipo Role
public class Role {
	private Integer id;// llave
	private String roleName;// nombre visible que se muestra
	private String role;// en nombre role para Spring Security

	public Role() {
	}

	public Role(Integer id, String roleName, String role) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
