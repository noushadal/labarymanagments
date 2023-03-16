package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.Iservice.Iservice;
import com.library.Request.AddBook;
import com.library.Response.ApiResponse;

@RestController
@RequestMapping("/lab")
public class MainController 
{
	@Autowired
	private Iservice iservice;
	//=================================BOOK API============================//
	@PostMapping("/addBook")
	public ResponseEntity<?>  addBooks(@RequestBody AddBook addbook)
	{
		
		
		iservice.bookAdd(addbook);
		//return new  ResponseEntity<String>("book added suceffuly",HttpStatus.CREATED);
		return new   ResponseEntity<String>(new ApiResponse(true,"Book added Sucessfully by admin").getMessage(),HttpStatus.CREATED);
		//return ResponseEntity.ok().body(new ApiResponse(true, "Book added to student having id ::"+userId))
		
		
		
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<?>  deleteBook(@PathVariable Integer id)
	{
		iservice.deleteBook(id);
		return new ResponseEntity<>(new ApiResponse(true,"book remove by admin"),HttpStatus.OK);
	}
	
	//test after adding user
	@PostMapping("/assignBookToUser/{id}/{bookId}")
	public ResponseEntity<?>  AssignBookToUser(@PathVariable("id") Integer id, @PathVariable("bookId") Integer bookId)
	{
		
		iservice.addBookToUser(id, bookId);
		
		return new ResponseEntity<>(new ApiResponse(true,"book added to user"),HttpStatus.OK);
		
	}
	
	//===================================UserApi for adding user ===================//

}
