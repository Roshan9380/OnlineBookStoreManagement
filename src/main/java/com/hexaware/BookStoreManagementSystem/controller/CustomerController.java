package com.hexaware.BookStoreManagementSystem.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.BookStoreManagementSystem.bean.Login;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.exception.CustomerException;
import com.hexaware.BookStoreManagementSystem.service.CustomerService;


@CrossOrigin()
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	

	@PostMapping(value = "/createcustomer",consumes = "application/json")
	public String createCustomer(@RequestBody Customer customer) {
	
		Encoder encoder=Base64.getEncoder();
		String encrypt=encoder.encodeToString(customer.getPassword().getBytes());
		customer.setPassword(encrypt);
		int cid;
		try {
			cid = customerservice.createCustomer(customer);
			return "Customer added successfully with customer id" + cid; 
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ""+e.getMessage();
		}
		
	}
	
	
	@GetMapping(value="/get/{cid}",produces="application/json")
	public ResponseEntity<?> getCustomer(@PathVariable int cid)  {
		
		Customer c=null;
		 try {
			c=customerservice.fetchCustomerById(cid);
			Decoder decoder=Base64.getDecoder();
			String password=new String(decoder.decode(c.getPassword()));
			System.out.println("Password is"+password);
			return new ResponseEntity<Customer>(c,HttpStatus.OK);
		} catch (CustomerException e) {
			
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}

	}
	
	
	@GetMapping(value="/auth/{customerName}/{password}" ,produces="application/json")
	public ResponseEntity<?> authenticate(@PathVariable String customerName,@PathVariable String password) {
		Login login=new Login();
		login.setCustomerName(customerName);
		login.setPassword(password);
		Customer customer=customerservice.validate(login);
		if(customer!=null) {
			customer.setPassword(password);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Invalid CustomerName or password",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();	
		return "logged out successfully";
	}
	
}