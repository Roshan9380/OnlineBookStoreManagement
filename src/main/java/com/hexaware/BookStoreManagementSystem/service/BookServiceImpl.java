package com.hexaware.BookStoreManagementSystem.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hexaware.BookStoreManagementSystem.entity.Book;
import com.hexaware.BookStoreManagementSystem.exception.BookException;
import com.hexaware.BookStoreManagementSystem.repository.BookRepository;



@Repository
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository brepo;
	
	@Transactional
	public int addBook(Book book) throws BookException {
		
		List<Book> books=(List)fetchAll();
		Book book_temp=null;
		for(Book b:books) {
			if( b.getBookName().equals(book.getBookName())) {
				book_temp=b;
			}
		}
		
		if(book_temp==null) {
			brepo.save(book);
			return book.getBookId();
		}else {
			throw new BookException("Book already exists with book id "+book_temp.getBookId());
		}
	}

	@Override
	public Collection<Book> fetchAll() {
		List<Book> books=brepo.findAll();
		return books;
	}    

	@Override
	public Book fetchBook(String bookName) throws BookException {
		
		System.out.println(bookName);               
		List<Book> books=(List)fetchAll();
		Book book=null;
		for(Book b:books) {
			if((b.getBookName().equals(bookName))) {
				book=b;
			}
		}
		
		if(book!=null) {
			return book;
		}else {
			throw new BookException("Book not found with provided details");
		}
		
	}
	
	@Override
	public Collection<Book> fetchBookOnCondition(String bookName)
			throws BookException {
		List<Book> books;
		books = brepo.findByCondition(bookName);
		if(books!=null) {
			return books;
		}else {
			throw new BookException("Books not found with provided details");
		}
		
	}


	@Transactional  
	public int updateBook(Book book) throws BookException {
		
		List<Book> books=(List)fetchAll();
		Book book1=null;
		for(Book b:books) {
			if(b.getBookId()==book.getBookId()) {
				book1=b;
			}
		}
		
		if(book1!=null) {
			book1.setBookId(book.getBookId());
			book1.setBookName(book.getBookName());
			book1.setBookPrice(book.getBookPrice());
			book1.setBookStatus(book.getBookStatus());
			brepo.save(book1);
			return book.getBookId();
		}else {
			throw new BookException("Book not found with id "+book.getBookId());
		}

		
	}

	@Transactional
	@Override
	public void removeBook(int bookId) {
		
		brepo.deleteById(bookId);
		System.out.println("Deleted book");
	}

	@Override
	public Book fetchById(int bid) {
		Book book = brepo.findById(bid).get();
		return book;
	}

}
