package com.hexaware.BookStoreManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.BookStoreManagementSystem.entity.Order;
import com.hexaware.BookStoreManagementSystem.entity.OrderDetails;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.repository.OrderRepository;
import com.hexaware.BookStoreManagementSystem.repository.OrderDetailsRepository;
import com.hexaware.BookStoreManagementSystem.repository.CustomerRepository;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepository crepo;
	
	@Autowired
	private CustomerRepository orepo;

	
	@Autowired
	private OrderDetailsRepository odrepo;
	
	
	
	@Override
	public int addOrder(Order order) {
		orepo.save(order);
		return order.getOrderId();
	}

	@Override
	public OrderDetails generateOrderDetails(OrderDetails orderDetails, int customerId, int orderId) {
		Order order = orepo.findById(orderId).get();
		Customer customer = crepo.findById(customerId).get();
		orderDetails.setOrder(order);
		orderDetails.setCustomer(customer);
	
		odrepo.save(orderDetails);
		return orderDetails;
	}
	
	
	@Override
	public void updateOrder(Order orderTAmount) {
		orepo.save(orderTAmount);
	}

	
	@Override
	public List<OrderDetails> getOrderDetails(int cid) {
		Customer customer=crepo.findById(cid).get();
		List<OrderDetails> odlist=odrepo.findByCustomer(customer);
		return odlist;
	}

	
	@Override
	public Order getOrderById(int oid) {
		
		return orepo.findById(oid).get();
	}

}
