package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	private List<Role> roles;

	public RoleServiceImpl() {
		this.roles = new ArrayList<Role>();
		roles.add(new Role(1, "Administrator", "ROLE_ADMIN"));
		roles.add(new Role(1, "User", "ROLE_USER"));
		roles.add(new Role(1, "Moderator", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> getRoles() {
		return this.roles;
	}

	@Override
	public Role getRoleById(Integer id) {
		Role result = null;
		for (Role role : roles) {
			if (id == role.getId()) {
				result = role;
			}
		}
		return result;
	}

}
