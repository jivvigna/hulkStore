package com.kardex.springboot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Employee;
import com.kardex.springboot.model.Role;
import com.kardex.springboot.service.IEmployeeService;
import com.kardex.springboot.service.IRoleService;


@SpringBootTest
class EmployeeTest {
	
	private static Logger LOG =  LoggerFactory.getLogger(EmployeeTest.class);

	@Autowired 
	private IEmployeeService employeeService;
	
	@Autowired 
	private IRoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void insertEmployee() {
		
		LOG.info("Iniciando Test insertEmployee");

		Employee employee = new Employee();
		employee.setFirstName("ADMIN1-TESTCASE");
		employee.setLastName("ADMIN1-TESTCASE");
		employee.setEmail("admin1@admin.com");
		employee.setUserName("ADMIN1");
		employee.setStatus(EntityStatus.ACTIVE);
		employee.setPassword(encoder.encode("12345678"));
		
		Employee employeeRes = employeeService.save(employee);
		
		assertTrue(employeeRes.getPassword().equalsIgnoreCase(employee.getPassword()));
		
		LOG.info("Finalizando Test insertEmployee");
		
	}
	
	@Test
	void modifyEmployee() {
		
		LOG.info("Iniciando Test modifyEmployee");
		
		List<Employee> employees = employeeService.findAll();
		Employee employee = new Employee();

		if (employees == null) { 
			this.insertEmployee();
			employee = employeeService.findAll().get(0);
		}
		else {
			employee = employees.get(0);
		}
		
		employee.setFirstName("ADMIN1-MOD");
		employee.setLastName("ADMIN1-MOD");
		
		Employee employeeRes = employeeService.modify(employee);

		assertTrue(employeeRes.getFirstName().equals(employee.getFirstName()));
		
		LOG.info("Finalizando Test modifyEmployee");
		
	}
	
	@Test
	void deleteEmployee() {
		
		LOG.info("Iniciando Test deleteEmployee");
		
		Integer sizeBefore = employeeService.findAll().size();

		List<Employee> employees = employeeService.findAll();
		Employee employee = new Employee();

		if (employees == null) { 
			this.insertEmployee();
			employee = employeeService.findAll().get(0);
		}
		else {
			employee = employees.get(0);
		}
		
		employeeService.delete(employee);
		
		Integer sizeAfter = employeeService.findAll().size();
		
		assertTrue(sizeAfter<sizeBefore);
		
		LOG.info("Finalizando Test deleteEmployee");
		
	}
	
	@Test
	void insertEmployeeWhitRole() {
		
		LOG.info("Iniciando Test insertEmployeeWhitRole");

		Employee employee1 = new Employee();
		employee1.setFirstName("ADMIN1_WHIT_ROLE");
		employee1.setLastName("ADMIN1_WHIT_ROLE");
		employee1.setEmail("adminwhitrole@admin.com");
		employee1.setUserName("ADMIN1");
		employee1.setPassword(encoder.encode("12345678"));
		employee1.setStatus(EntityStatus.ACTIVE);
		
		
		List<Role> roles = roleService.findAll();

		Role role = new Role();
		if (roles == null){
			role = new Role();
			role.setName("ADMIN-TESTCASE");
			role.setEmployees(new ArrayList<Employee>());
		}
		else {
			role = roles.get(0);
		}
		
		role.getEmployees().add(employee1);
		
		Role res = roleService.save(role);
		
		boolean result = false;
		
		for (Iterator<Employee> iterator = res.getEmployees().iterator(); iterator.hasNext();) {
			Employee employee = iterator.next(); 
			if(employee.getFirstName().equals(employee1.getFirstName()) && employee.getPassword().equalsIgnoreCase(employee1.getPassword())){
				result = true;
			}
				
		}
		
		assertTrue(result);
		
		LOG.info("Iniciando Test insertEmployeeWhitRole");
		
	}
	
	
	

}
