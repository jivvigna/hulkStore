package com.kardex.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kardex.springboot.service.IPurchaseService;


@SpringBootTest
class PurchaseTest {
	
	private static Logger LOG =  LoggerFactory.getLogger(PurchaseTest.class);

	@Autowired 
	private IPurchaseService purchaseService;
	
	@Test
	void insertPurchase() {
	}
	
	@Test
	void modifyPurchase() {
	}
	
	@Test
	void deletePurchase() {
	}

}
