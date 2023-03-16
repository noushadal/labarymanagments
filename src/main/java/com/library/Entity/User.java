package com.library.Entity;



import java.util.Calendar;
import java.util.Set;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String username;
	
	
	@Email
	private String email;
	
	
	private String password;
	
	private Calendar issueDate;
	
	private Integer Amount;
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL,mappedBy ="user")
	 * 
	 * @JsonManagedReference private Set<Books> books;
	 */
private Calendar returnDate;
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id")
			
			)
	private Set<Role>  role;
	
	@ManyToMany(cascade = CascadeType.ALL)
	
	@JsonManagedReference
	private Set<Books>  books;
	
	



	public User() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	




	public User(String name, String username, @Email String email, String password) {
		super();
		
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}






	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Calendar getIssueDate() {
		return issueDate;
	}



	public void setIssueDate(Calendar issueDate) {
		this.issueDate = issueDate;
	}



	public Integer getAmount() {
		return Amount;
	}



	public void setAmount(Integer amount) {
		Amount = amount;
	}



	public Calendar getReturnDate() {
		return returnDate;
	}



	public void setReturnDate(Calendar returnDate) {
		this.returnDate = returnDate;
	}



	public Set<Role> getRole() {
		return role;
	}



	public void setRole(Set<Role> role) {
		this.role = role;
	}



	public Set<Books> getBooks() {
		return books;
	}



	public void setBooks(Set<Books> books) {
		this.books = books;
	}
	



	
	
	

	



}
