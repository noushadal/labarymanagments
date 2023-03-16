package com.library.Request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AddBook 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	
	private String title;
	
	private Integer totalBook;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(Integer l) {
		this.totalBook = l;
	}

	public AddBook(Integer bookId, String title, Integer totalBook) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.totalBook = totalBook;
	}

	public AddBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
