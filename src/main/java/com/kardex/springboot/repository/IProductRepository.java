package com.kardex.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.springboot.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findByStatus(String status);
	
}
