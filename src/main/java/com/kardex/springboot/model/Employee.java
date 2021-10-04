package com.kardex.springboot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/***
 * 
 * @author jvigna
 * 
 * Representa la entidad empleados 
 *
 */

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@NotNull
	@Size(min=2, max=50, message = "El Nombre de tener entre 2 y 50 caracteres")
	@Column(name = "firstName", length = 50, nullable = false)
	private String firstName;

	@NotNull
	@Size(min=2, max=50, message = "El Apellido de tener entre 2 y 50 caracteres")
	@Column(name = "lastName", length = 50, nullable = false)
	private String lastName;
	
	@NotNull
	@Size(min=2, max=50, message = "El Email de tener entre 2 y 50 caracteres")
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@NotNull
	@Size(min=5, max=30, message = "El Usuario de tener entre 5 y 30 caracteres")
	@Column(name = "userName" , length = 30, nullable = false)
	private String userName;
	
	@NotNull
	@Size(min=8, max=100, message = "La contraseña de tener entre 8 y 100 caracteres")
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "status", length = 20, nullable = false)
	private String status;
	
	@ManyToMany(mappedBy = "employees")
    private List<Role> roles;
	
    @OneToMany(mappedBy="employee")
    private List<Purchase> purchases;

	public Employee() {
		
	}

	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	 * @return the purchases
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * @param purchases the purchases to set
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	

}
