package com.hexaware.BookStoreManagementSystem.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.BookStoreManagementSystem.entity.Book;
import com.hexaware.BookStoreManagementSystem.entity.Customer;
import com.hexaware.BookStoreManagementSystem.exception.BookException;
import com.hexaware.BookStoreManagementSystem.service.BookService;


@CrossOrigin()
@EnableTransactionManagement
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bservice;
	


	@PostMapping(value = "/add",consumes = "application/json")
	public String addBook(@RequestBody Book book, HttpSession session) {
		try {		
				int id=bservice.addBook(book);
				return "Book added with book id "+id;
			
		} catch (BookException e) {
			e.printStackTrace();
			return ""+e.getMessage();
		}
		
	}
	

	@GetMapping(value="/fetchall",produces="application/json")
	public Collection<Book> searchFlights(){
		return bservice.fetchAll();	
	}
	

	@GetMapping(value="/fetch",produces="application/json")  
	public ResponseEntity<?> searchBook(@RequestParam String bookName) {
		try {
			
			Collection<Book> books = bservice.fetchBookOnCondition(bookName);
			return new ResponseEntity< Collection<Book> >(books,HttpStatus.OK);
			
		} catch (BookException e) {
			
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	


	@DeleteMapping(value="/remove/{bid}")
	public String removeBook(@PathVariable int bid, HttpSession session) {

			bservice.removeBook(bid);
			return "book removed with id" + bid;

		
	}
	

	@PutMapping(value="/update",produces="application/json")
	public String updateBook(@RequestBody Book book, HttpSession session) {
		try {

				int id=bservice.updateBook(book);
				return "Book updated with id "+id;
			
		} catch(BookException e) {
			
			e.printStackTrace();
			return ""+e.getMessage();
		}
	}
}
