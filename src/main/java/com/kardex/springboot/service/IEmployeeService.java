package com.kardex.springboot.service;

import java.util.List;

import com.kardex.springboot.model.Employee;

public interface IEmployeeService {
	
	public Employee save(Employee employee);

	public Employee modify(Employee employee);

	public void deleteByid(Long id);
	
	public void delete(Employee id);
	
	public List<Employee> findAll();
	
	public Employee findByUserName(String userName);
	
	public List<Employee> findByStatus(String status);
	
	public Employee getEmployeeSession();

}
