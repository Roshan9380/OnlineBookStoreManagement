package com.hexaware.BookStoreManagementSystem.service;

import java.util.Collection;

import com.hexaware.BookStoreManagementSystem.entity.Book;
import com.hexaware.BookStoreManagementSystem.exception.BookException;

public interface BookService {
	
	 int addBook(Book book) throws BookException;
	
	 Collection<Book> fetchAll();
	
	 Book fetchBook(String bookName) 
			throws BookException;
	 
	 Collection<Book> fetchBookOnCondition(String bookName) 
				throws BookException;
	
	 int updateBook(Book book) throws BookException; 
	 
	 void removeBook(int bookId);
	 
	 Book fetchById(int bid);
}