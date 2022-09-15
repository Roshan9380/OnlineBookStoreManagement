package com.hexaware.BookStoreManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.BookStoreManagementSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByCustomerNameAndPassword(String customerName,String password);
	
}