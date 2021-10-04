package com.kardex.springboot;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Employee;
import com.kardex.springboot.model.Product;
import com.kardex.springboot.model.Role;
import com.kardex.springboot.service.IProductService;
import com.kardex.springboot.service.IRoleService;

@SpringBootTest
class SpringbootBackendApplicationTests {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void insertInitial() {
		
		
		/*Creando roles y usuarios por defecto*/
		Role role = new Role();
		role.setName("ADMIN");
		
		List<Employee> employees = new ArrayList<Employee>(); 

		for (int i= 1; i<=5; i++) {
			Employee employee = new Employee();
			
			employee.setUserName("ADMIN"+i);
			employee.setFirstName("NAME-ADMIN"+i);
			employee.setLastName("LASTNAME-ADMIN"+i);
			employee.setEmail("admin"+i+"@todo1.com");
			employee.setPassword(encoder.encode("12345678"));
			employee.setStatus(EntityStatus.ACTIVE);
			
			employees.add(employee);
			
		}
		
		role.setEmployees(employees);
		
		roleService.save(role);

		/*Creando Productos por defecto*/
		for (int i= 1; i<=10; i++) {
		
			Product product = new Product();
			product.setDescription("PRODUCT-DESC-" + i);
			product.setName("PRODUCT-NAME-" + i);
			product.setPrice(10.0 * i);
			product.setStock(50);
			product.setStatus(EntityStatus.ACTIVE);
			productService.save(product);
		
		}

		
	}
	

}
