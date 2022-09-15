package com.hexaware.BookStoreManagementSystem.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="book")
@SequenceGenerator(name = "book_seq",sequenceName = "book_seq",initialValue = 1)
public class Book {
	@Id
	@GeneratedValue(generator = "book_seq",strategy=GenerationType.SEQUENCE)
	private int bookId;
	@Column(name="book_name")
	private String bookName;
	@Column(name="book_price")
	private double bookPrice;
	private String bookStatus;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
}	
	
	
	
	