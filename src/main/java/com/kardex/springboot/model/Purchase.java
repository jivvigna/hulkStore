package com.kardex.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")

public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@Column(name = "total", nullable = false)
	private Double total;
    
	@Column(name = "status",length = 20, nullable = false)
	private String status;
	
	@Column(name = "statusPurchase",length = 20, nullable = false)
	private String statusPurchase;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employeeId", nullable=false)
    private Employee employee;	
	
    @OneToMany(mappedBy="purchase", cascade = CascadeType.ALL)
    private List<ProductPurchase> productPurchases;

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
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the productPurchases
	 */
	public List<ProductPurchase> getProductPurchases() {
		return productPurchases;
	}

	/**
	 * @param productPurchases the productPurchases to set
	 */
	public void setProductPurchases(List<ProductPurchase> productPurchases) {
		this.productPurchases = productPurchases;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the statusPurchase
	 */
	public String getStatusPurchase() {
		return statusPurchase;
	}

	/**
	 * @param statusPurchase the statusPurchase to set
	 */
	public void setStatusPurchase(String statusPurchase) {
		this.statusPurchase = statusPurchase;
	}
	
}
