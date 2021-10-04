package com.kardex.springboot.service;

import java.util.List;

import com.kardex.springboot.model.ProductPurchase;

public interface IProductPurchaseService {
	
	public ProductPurchase save(ProductPurchase productPurchase);

	public ProductPurchase modify(ProductPurchase productPurchase);

	public List<ProductPurchase> findAll();
	
}
