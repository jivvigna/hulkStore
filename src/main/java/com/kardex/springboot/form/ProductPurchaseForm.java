package com.kardex.springboot.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductPurchaseForm {
	
	private long id;
	
	@NotNull(message = "Debe de ser mayor a cero")
	@Min(value = 1, message = "Debe de ser mayor a cero")
	private Integer quantity;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	

	
}
