package com.example.business.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findById(Long id);
	Book findByBookName(String bookName);
	
	@Modifying
	@Transactional
	@Query("UPDATE Book book set book.bookName = :bookName, book.author = :author, book.number = :number, book.updatedAt = :updatedAt, book.deleteFlag = :deleteFlag WHERE book.id = :id")
	Integer updateBook(@Param("id") Long id,
				   	   @Param("bookName") String bookName, 
				   	   @Param("author") String author, 
				   	   @Param("number") Integer number, 
				   	   @Param("updatedAt") Timestamp ts,
				   	   @Param("deleteFlag") boolean flag);
	
	@Query("SELECT book FROM Book book WHERE book.deleteFlag = false and book.bookName LIKE %:bookName%")
	List<Book> findAllByBookName(@Param("bookName") String bookName);
	
	@Query("SELECT book FROM Book book WHERE book.deleteFlag = false and book.author LIKE %:author%")
	List<Book> findAllByAuthor(@Param("author") String author);
	
	@Query("SELECT book FROM Book book WHERE book.deleteFlag = false and book.bookName LIKE %:bookName% and book.author LIKE %:author%")
	List<Book> find(@Param("bookName") String bookName, @Param("author") String author );
}
