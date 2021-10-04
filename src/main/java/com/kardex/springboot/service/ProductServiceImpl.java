package com.kardex.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Product;
import com.kardex.springboot.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private IProductRepository productRepository;
	

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}


	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}


	@Override
	public Product modify(Product product) {
		return productRepository.save(product);
	}


	@Override
	public void deleteById(Long id) {
		Product product = productRepository.getById(id);
		product.setStatus(EntityStatus.DELETED);
		productRepository.save(product);
	}


	@Override
	public List<Product> findByStatus(String status) {
		return productRepository.findByStatus(status);
	}


	@Override
	public Product findById(Long id) {
		return productRepository.getById(id);
	}


	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

}
