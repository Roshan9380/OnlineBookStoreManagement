package com.hexaware.BookStoreManagementSystem.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="customer")
@SequenceGenerator(name = "customer_seq",sequenceName = "customer_seq",initialValue = 1)
public class Customer {
	
	@Id
	@GeneratedValue(generator = "customer_seq",strategy=GenerationType.SEQUENCE)
	@Column(name = "customer_id")
	private int customerId;
	
	private String customerName;
	
	@Column(name="customer_fullname")
	private String customerFName;
	
	private String customerEmail;
	
	private String customerPhone;
	
	private int isadmin;
	
	private String password;
	
	
	public Customer() {
		
	}


	public Customer(String customerName, String customerFName, String customerEmail, String customerPhone, int isadmin,
			String password) {
		super();
		this.customerName = customerName;
		this.customerFName = customerFName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.isadmin = isadmin;
		this.password = password;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerFName() {
		return customerFName;
	}


	public void setCustomerFName(String customerFName) {
		this.customerFName = customerFName;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public int getIsadmin() {
		return isadmin;
	}


	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
