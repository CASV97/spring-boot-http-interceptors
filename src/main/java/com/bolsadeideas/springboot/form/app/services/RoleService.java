package com.bolsadeideas.springboot.form.app.services;

import java.util.List;

import com.bolsadeideas.springboot.form.app.models.entity.Role;

public interface RoleService {
	public List<Role> getRoles();

	public Role getRoleById(Integer id);
}
