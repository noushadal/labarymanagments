package com.library.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Books 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	
	private String title;
	
	private Integer totalBook;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "role",fetch = FetchType.LAZY)
    @JsonBackReference
	private Set<User> user;

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

	public void setTotalBook(Integer totalBook) {
		this.totalBook = totalBook;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Books(Integer bookId, String title, Integer totalBook, Set<User> user) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.totalBook = totalBook;
		this.user = user;
	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	





	





	
	

}
