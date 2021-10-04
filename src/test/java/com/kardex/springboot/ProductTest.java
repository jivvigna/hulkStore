package com.kardex.springboot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.model.Product;
import com.kardex.springboot.service.IProductService;


@SpringBootTest
class ProductTest {
	
	private static Logger LOG =  LoggerFactory.getLogger(ProductTest.class);

	@Autowired 
	private IProductService productService;
	
	@Test
	void insertProduct() {
		
		LOG.info("Iniciando Test insertProduct");

		Product product1 = new Product();
		product1.setDescription("PRODUCT-TESTCASE-1");
		product1.setName("PRODUCT-TESTCASE-1");
		product1.setPrice(10.0);
		product1.setStock(50);
		product1.setStatus(EntityStatus.ACTIVE);
		
		Product product1Res = productService.save(product1);

		assertTrue(product1Res.getPrice().equals(product1.getPrice()));
		
		LOG.info("Finalizando Test insertProduct");
		
	}
	
	@Test
	void modifyProduct() {
		
		LOG.info("Iniciando Test modifyProduct");

		List<Product> products = productService.findAll();
		
		Product product = new Product();
		if (products == null) { 
			this.insertProduct();
			product = productService.findAll().get(0);
		}
		else {
			product = products.get(0);
		}
		
		product.setName("PRODUCT-TESTCASE-1-MOD");
				
		Product res = productService.save(product);

		assertTrue(res.getName().equals(product.getName()));
		
		LOG.info("Finalizando Test modifyProduct");		
	}
	
	@Test
	void deleteProduct() {
		
		LOG.info("Iniciando Test deleteProduct");
		
		Integer sizeBefore = productService.findAll().size();

		List<Product> products = productService.findAll();
		
		Product product = new Product();
		if (products == null) { 
			this.insertProduct();
			product = productService.findAll().get(0);
		}
		else {
			product = products.get(0);
		}
		
		productService.delete(product);
		
		Integer sizeAfter = productService.findAll().size();
		
		assertTrue(sizeAfter<sizeBefore);
		
		LOG.info("Finalizando Test deleteProduct");
		
	}
	
	

}
