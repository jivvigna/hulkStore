package com.kardex.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Employee;
import com.kardex.springboot.model.Role;
import com.kardex.springboot.repository.IEmployeeRepository;
import com.kardex.springboot.repository.IRoleRepository;

@Service
public class EmployeeServiceImpl implements UserDetailsService, IEmployeeService{

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	private static Logger LOG =  LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LOG.info("Inicio - Validando credenciales");
		
		Employee user = this.findByUserName(username);
		
		Role role = roleRepository.findByName("ADMIN");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if (role != null){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		else {
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
		}
		
		UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
		
		LOG.info("Fin - Validando credenciales");
		
		return userDetail;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
		
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee modify(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteByid(Long id) {

		Employee employee = employeeRepository.getById(id);
		employee.setStatus(EntityStatus.DELETED);
		employeeRepository.save(employee);
	}

	@Override
	public Employee findByUserName(String userName) {
		return employeeRepository.findByUserName(userName);
	}

	@Override
	public List<Employee> findByStatus(String status) {
		return employeeRepository.findByStatus(status);
	}

	@Override
	public Employee getEmployeeSession() {
		
		 Authentication auth = SecurityContextHolder .getContext().getAuthentication(); 
		 UserDetails userDetail = (UserDetails)auth.getPrincipal(); 
		 String usuario = userDetail.getUsername();
		 
		 return this.findByUserName(usuario);
		
	}

	@Override
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

}
