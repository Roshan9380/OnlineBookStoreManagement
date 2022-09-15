package com.hexaware.BookStoreManagementSystem.service;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.BookStoreManagementSystem.bean.Login;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.exception.CustomerException;
import com.hexaware.BookStoreManagementSystem.repository.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerrepo;
	
	@Override
	public int createCustomer(Customer customer) throws CustomerException {
		List<Customer> customers=(List)fetchAllCustomers();
		Customer customer_temp=null;
		for(Customer c:customers)
		{
			if(c.getCustomerName().equals(customer.getCustomerName()) && (c.getCustomerEmail().equals(customer.getCustomerEmail())) )
				customer_temp=c;
		}
		
		if(customer_temp==null) {
			customerrepo.save(customer);
			return customer.getCustomerId();
		}else {
			throw new CustomerException("Customer already exist with customerId " + customer_temp.getCustomerId());
		}
	}

	@Override
	public Customer fetchCustomerById(int customer_id) throws CustomerException {
		
		List<Customer> customers=(List)fetchAllCustomers();
		Customer customer=null;
		for(Customer c:customers) {
			if(c.getCustomerId()==customer_id) {
				customer=c;
			}
		}
		if(customer!=null) {
			return customerrepo.findById(customer_id).get();
		}else {
			throw new CustomerException("Customer not found with id"+customer_id);
		}
		
		
	}

	
	@Override
	public Customer validate(Login login) {
	
		Encoder encoder=Base64.getEncoder();
		String encrypt=encoder.encodeToString(login.getPassword().getBytes());
		Customer customer=customerrepo.findByCustomerNameAndPassword(login.getCustomerName(), encrypt);
		return customer;
	}
	
	@Override
	public Collection<Customer> fetchAllCustomers() {
		List<Customer> customers=customerrepo.findAll();
		return customers;
	}

}
