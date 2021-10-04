package com.kardex.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.springboot.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByName(String userName);
	
}
