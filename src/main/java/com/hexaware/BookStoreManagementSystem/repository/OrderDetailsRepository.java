package com.hexaware.BookStoreManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.BookStoreManagementSystem.entity.OrderDetails;
import com.hexaware.BookStoreManagementSystem.entity.Customer;

import java.util.List;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	List<OrderDetails> findByUser(Customer customer);
}

