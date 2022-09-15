package com.hexaware.BookStoreManagementSystem.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "orderdetails_seq",sequenceName = "orderdetails_seq",initialValue = 5001)
public class OrderDetails {
	
	@Id
	@GeneratedValue(generator = "orderdetails_seq",strategy=GenerationType.SEQUENCE)
	@Column(name = "orderdetails_id")
	private int orderDetailsId;
	

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private LocalDate orderDate;
	private double orderAmount;
	
	public OrderDetails() {
	}

	public OrderDetails(Customer customer, Order order, LocalDate orderDate, double orderAmount) {
		super();
		this.customer = customer;
		this.order = order;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

}