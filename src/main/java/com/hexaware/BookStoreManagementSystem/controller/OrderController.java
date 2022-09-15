package com.hexaware.BookStoreManagementSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.BookStoreManagementSystem.entity.Order;
import com.hexaware.BookStoreManagementSystem.entity.Book;
import com.hexaware.BookStoreManagementSystem.entity.OrderDetails;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.exception.BookException;
import com.hexaware.BookStoreManagementSystem.service.OrderService;
import com.hexaware.flightbookingsystem.entity.Booking;
import com.hexaware.flightbookingsystem.entity.Flight;
import com.hexaware.flightbookingsystem.exception.FlightException;
import com.hexaware.BookStoreManagementSystem.service.BookService;



@CrossOrigin()
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
	@Autowired
	private BookService bookservice;
	

	@PostMapping(value = "/order", consumes = "application/json")
	public String addOrder(@RequestBody Order order, @RequestParam int bid, String bookName) throws BookException {
		

		
		Book book = bookservice.fetchById(bid);
		if(book.getAvailableBooks()<=0) {
			return "Books are not available";
		}else if(order.getNumberOfBooksToBook()>book.getAvailableBooks()) {
			return "Only "+book.getAvailableBooks()+" are Available";
		}else {
			book.setAvailableBooks(book.getAvailableBooks()-order.getNumberOfBooksToBook());
			bookservice.updateBook(book);
			order.setBook(book);
			order.setOrderDate(LocalDate.now());
			int oid = orderservice.addOrder(order);
			return "" + oid;
		}
	}
	
	
	@PostMapping(value = "/orderDetails/customerId}/{orderId}/{orderTAmount}", consumes = "application/json",produces = "application/json")
	public ResponseEntity<?> createOrderD(@RequestBody OrderDetails orderDetails, @PathVariable int customerId, @PathVariable int orderId, @PathVariable int orderTAmount) {
		

		Order order=orderservice.getOrderById(orderId);
		order.setOrderTAmount(orderTAmount);
		orderservice.updateOrder(order);
		
		int pay_status = order.getPayStatus();
		double total_pay=order.getBook().getPrice()*order.getNumberOfBooksToBook();
		if(pay_status==1) {
			LocalDate date = LocalDate.now();
			orderDetails.setOrder_date(date);
			orderDetails.setTotal_pay(total_pay); 
			orderDetails orderDetails1 = orderservice.generateOrderDetails(orderDetails, customerId, orderId);
			
			return new ResponseEntity<OrderDetails>(orderDetails1, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Payment failed, please book ticket again.",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getTickets/{uid}" ,produces = "application/json")
	public List<OrderDetails> getAllOrderDetails(@PathVariable int cid) {
		orderservice.getOrderDetails(cid);
		return orderservice.getOrderDetails(cid);
		
	}
	

}
