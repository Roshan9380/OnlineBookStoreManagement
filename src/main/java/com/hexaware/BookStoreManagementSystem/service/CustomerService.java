package com.hexaware.BookStoreManagementSystem.service;

import java.util.Collection;

import com.hexaware.BookStoreManagementSystem.bean.Login;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.exception.CustomerException;

public interface CustomerService {
	
	 int createCustomer(Customer customer) throws CustomerException;
	
	 Customer fetchCustomerById(int customer_id) throws CustomerException;
	 
	 Customer validate(Login login);
	
	 public Collection<Customer> fetchAllCustomers();
	
}
