package com.library.serviceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Entity.Books;
import com.library.Entity.User;
import com.library.Iservice.Iservice;
import com.library.Request.AddBook;
import com.library.Request.AssignBookToUser;
import com.library.repository.BooksRepository;
import com.library.repository.UserRepository;
@Service
public class ServiceImpl implements Iservice
{
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//==================Conversion======================//
	
	public AddBook BooksToAddBooks(Books books)
	{
		AddBook addBook=new AddBook();
		
		addBook.setBookId(books.getBookId());
		addBook.setTitle(books.getTitle());
		addBook.setTotalBook(books.getTotalBook());
   return  addBook;	
   }
	
	public Books AddBooksToBooks(AddBook addBook)
	{
		Books books=new Books();
		books.setBookId(addBook.getBookId());
		books.setTitle(addBook.getTitle());
		books.setTotalBook(addBook.getTotalBook());
		return books;
		
	}
	
	//===========================Conversion done=================================//

	@Override
	public AddBook bookAdd(AddBook addbook)
	{

      
      Books book = booksRepository.findByTitle(addbook.getTitle());
      
      if(book != null)
      {
    	  int total= book.getTotalBook();
    	  book.setTotalBook(total + 1);
    	  booksRepository.save(book);
    	  return this.BooksToAddBooks(book);
      }
      
      else {
    	  Books books2 = new Books();
    	  books2.setTitle(addbook.getTitle());
    	  books2.setTotalBook(1);
    	  books2.setTotalBook(addbook.getTotalBook());
    	  booksRepository.save(books2);
    	  return this.BooksToAddBooks(books2);
      }
	}
	
	@Override
	public AddBook deleteBook(Integer id) 
	{
	Books book	=booksRepository.findById(id).get();
	
	if(book!=null)
	{
	 Integer  total	=book.getTotalBook();
	 
	      book.setTotalBook(total-1);
	      
	  Books b1    =booksRepository.save(book);
	}
	else
	{
		booksRepository.delete(book);
		
	}
	return this.BooksToAddBooks(book);
	
	}

	@Override
	public String addBookToUser(Integer id, Integer bookId) 
	{
		Set<User>  user=new HashSet<>();
		Books book  =booksRepository.findById(bookId).get();
		User user1=userRepository.findById(id).get();
	    Integer totalBook   =book.getTotalBook();	
	    if(book.getBookId()!=null)
	    {
		user=book.getUser();
		user.add(user1);
		book.setTotalBook(totalBook-1);
		user1.setIssueDate(Calendar.getInstance());
	    }
		booksRepository.save(book);
		userRepository.save(user1);
		return "book assing to user";
	}

//	@Override
//	public AssignBookToUser addBookToUser(AssignBookToUser assignBookToUser)
//	{
//		
//		Set<User> user=new HashSet<>();
//		
//		int count=0;
//		Set<User> userlist=null;
//	Books book	=booksRepository.findByTitle(assignBookToUser.getTitle()).get();
//	User user	=userRepository.findAllById(assignBookToUser.getUser()); 
//	userlist =book.getUser();
//	userlist.add(user);
//	
//		
//			//count++;
//			//book.setTotalBook(count);
//			book.setUser(userlist);
//	
//	
//	
//	
//	   userAddBooks addBooks=new userAddBooks();
//	   System.out.println(LocalDateTime.now());
//	   user.setIssueDate(LocalDateTime.now());
//	   
//	 
//	   
//	
//	
//		 booksRepository.save(book);
//		 userRepository.save(user);
//		 return "data saved";
//		
//		return null;
//	}


      
      
     
    		  
    		  
    		  
      
    
		
		
		
	


}
