package com.kardex.springboot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kardex.springboot.model.Role;
import com.kardex.springboot.service.IRoleService;

@SpringBootTest
class RoleTest {

	@Autowired 
	private IRoleService roleService;
	
	private static Logger LOG =  LoggerFactory.getLogger(EmployeeTest.class);	
	
	
	@Test
	void insertRole() {
		
		LOG.info("Iniciando Test insertRole");

		Role role = new Role();
		role.setName("ADMIN-TESTCASE");
		
		Role res = roleService.save(role);
		
		LOG.info("Finalizando Test insertRole");
		
		assertTrue(res.getName().equalsIgnoreCase(role.getName()));
		
	}
	
	@Test
	void modifyRole() {
		
		LOG.info("Iniciando Test modifyRole");

		Role role = roleService.findAll().get(1);
		role.setName("ADMIN-TESTCASE-MOD");
				
		Role res = roleService.save(role);

		assertTrue(res.getName().equals(role.getName()));
		
		LOG.info("Finalizando Test modifyRole");
		
	}
	
	@Test
	void deleteRole() {
		
		LOG.info("Iniciando Test deleteEmployee");
		
		Integer sizeBefore = roleService.findAll().size();

		Role role = roleService.findAll().get(1);
		
		roleService.delete(role);
		
		Integer sizeAfter = roleService.findAll().size();
		
		assertTrue(sizeAfter<sizeBefore);
		
		LOG.info("Finalizando Test deleteEmployee");
		
	}

}
