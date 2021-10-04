package com.kardex.springboot.service;

import java.util.List;

import com.kardex.springboot.model.Product;

public interface IProductService {
	
	public Product save(Product product);
	
	public Product modify(Product product);
	
	public void deleteById(Long id);
	
	public void delete(Product product);
	
	public List<Product> findAll();
	
	public List<Product> findByStatus(String status);
	
	public Product findById(Long id);
	
}
