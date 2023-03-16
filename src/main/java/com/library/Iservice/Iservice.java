package com.library.Iservice;

import com.library.Request.AddBook;
import com.library.Request.AssignBookToUser;

public interface Iservice 
{
	public AddBook  bookAdd(AddBook addbook);
	
	public AddBook  deleteBook(Integer id);
	
	public String  addBookToUser(Integer id, Integer bookId);

}
