package com.kardex.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Purchase;
import com.kardex.springboot.repository.IPurchaseRepository;

@Service
public class PurchaseServiceImpl implements IPurchaseService{

	@Autowired
	private IPurchaseRepository purchaseRepository;

	@Override
	public Purchase save(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	@Override
	public Purchase modify(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	@Override
	public void deleteById(Long id) {
		Purchase purchase = purchaseRepository.getById(id);
		purchase.setStatus(EntityStatus.DELETED);
		purchaseRepository.save(purchase);
	}

	@Override
	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}
	

}
