package com.kardex.springboot.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductForm {
	
	@NotNull
	@Size(min=2, max=50, message = "El Nombre de tener entre 2 y 50 caracteres")
	private String name;
	
	@NotNull
	@Size(min=2, max=100, message = "La descripción de tener entre 2 y 100 caracteres")
	private String description;
	
	@NotNull(message = "Debe de ser mayor a cero")
	@Min(value = 1, message = "Debe de ser mayor a cero")
	private Double price;
	
	@NotNull(message = "Debe de ser mayor a cero")
	@Min(value = 1, message = "Debe de ser mayor a cero")
	private Integer stock;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	

	
}
