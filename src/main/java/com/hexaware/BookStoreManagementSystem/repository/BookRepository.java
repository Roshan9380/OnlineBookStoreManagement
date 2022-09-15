package com.hexaware.BookStoreManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.BookStoreManagementSystem.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("FROM Book WHERE bookName=:bookName")
	List<Book> findByCondition(String bookName);

}