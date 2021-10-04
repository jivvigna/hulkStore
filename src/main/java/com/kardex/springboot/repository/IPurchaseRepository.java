package com.kardex.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.springboot.model.Purchase;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{
	
}
