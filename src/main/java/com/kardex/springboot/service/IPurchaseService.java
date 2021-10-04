package com.kardex.springboot.service;

import java.util.List;

import com.kardex.springboot.model.Purchase;

public interface IPurchaseService {
	
	public Purchase save(Purchase purchase);
	
	public Purchase modify(Purchase purchase);
	
	public void deleteById(Long id);
	
	public List<Purchase> findAll();
	
}
