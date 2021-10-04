package com.kardex.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.springboot.model.ProductPurchase;

@Repository
public interface IProductPurchaseRepository extends JpaRepository<ProductPurchase, Long>{
	

}
