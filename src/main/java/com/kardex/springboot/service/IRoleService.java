package com.kardex.springboot.service;

import java.util.List;

import com.kardex.springboot.model.Role;

public interface IRoleService {
	
	public Role save(Role role);
	
	public Role modify(Role role);
	
	public void delete(Role role);
	
	public Role findByName(String name);
	
	public List<Role> findAll();

}
