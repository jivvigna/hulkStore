package com.kardex.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.springboot.model.Role;
import com.kardex.springboot.repository.IRoleRepository;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository iRoleRepository;

	@Override
	public Role save(Role role) {
		return iRoleRepository.save(role);
	}

	@Override
	public Role modify(Role role) {
		return iRoleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		iRoleRepository.delete(role);
	}

	@Override
	public Role findByName(String name) {
		return iRoleRepository.findByName(name);
	}

	@Override
	public List<Role> findAll() {
		return iRoleRepository.findAll();
	}
	

}
