package com.kardex.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.springboot.model.ProductPurchase;
import com.kardex.springboot.repository.IProductPurchaseRepository;

@Service
public class ProductPurchaseServiceImpl implements IProductPurchaseService{

	@Autowired
	private IProductPurchaseRepository productPurchaseRepository;

	@Override
	public ProductPurchase save(ProductPurchase productPurchase) {
		return productPurchaseRepository.save(productPurchase);
	}

	@Override
	public ProductPurchase modify(ProductPurchase productPurchase) {
		return productPurchaseRepository.save(productPurchase);
	}

	@Override
	public List<ProductPurchase> findAll() {
		return productPurchaseRepository.findAll();
	}
	


}
