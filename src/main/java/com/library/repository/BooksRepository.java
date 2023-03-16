package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.Entity.Books;

public interface BooksRepository extends JpaRepository<Books, Integer>
{

	@Query("select b from Books b where b.title=?1")
	Books findByTitle (String title);
	
	
}
