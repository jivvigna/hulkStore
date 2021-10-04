package com.kardex.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.springboot.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Employee findByUserName(String userName);
	public List<Employee> findByStatus(String status);

}
