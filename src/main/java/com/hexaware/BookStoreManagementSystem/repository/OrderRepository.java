package com.hexaware.BookStoreManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.BookStoreManagementSystem.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
