package com.hexaware.BookStoreManagementSystem.service;

import java.util.List;

import com.hexaware.BookStoreManagementSystem.entity.Order;
import com.hexaware.BookStoreManagementSystem.entity.OrderDetails;

public interface OrderService {
	
	int addOrder(Order order);
	
	
	OrderDetails generateOrderDetails(OrderDetails orderdetails, int customerId, int orderId);
	
	List<OrderDetails> getOrderDetails(int cid);
	
	Order getOrderById(int oid);
	
	void updateOrder(Order orderTAmount);
	
}
