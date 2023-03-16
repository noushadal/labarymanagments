package com.library.Request;

import java.util.Calendar;
import java.util.Set;

import com.library.Entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AssignBookToUser 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
	
	private String title;
	
	private Integer id;
	
	private Integer totalBook;
	
	private Calendar issueDate;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(Integer totalBook) {
		this.totalBook = totalBook;
	}

	public Calendar getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Calendar issueDate) {
		this.issueDate = issueDate;
	}

	public AssignBookToUser(Integer bookId, String title, Integer id, Integer totalBook, Calendar issueDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.id = id;
		this.totalBook = totalBook;
		this.issueDate = issueDate;
	}

	public AssignBookToUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
