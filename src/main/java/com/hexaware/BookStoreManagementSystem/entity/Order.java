package com.hexaware.BookStoreManagementSystem.entity;

import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="order")
@SequenceGenerator(name = "order_seq",sequenceName = "order_seq",initialValue = 1)
public class Order {
	@Id
	@GeneratedValue(generator = "order_seq",strategy=GenerationType.SEQUENCE)
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name="order_qty")
	private int orderQuantity;
	
	private float orderTAmount;
	
	

	@OneToOne
	@JoinColumn(name = "book_id")		
	private Book book;


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getOrderQuantity() {
		return orderQuantity;
	}


	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}


	public float getOrderTAmount() {
		return orderTAmount;
	}


	public void setOrderTAmount(float orderTAmount) {
		this.orderTAmount = orderTAmount;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
	
}