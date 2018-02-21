package com.example.business.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.business.entity.Book;

public interface BookService {
	Book save(Book book);
	Book findOne(Long id);
	Integer updateBook(Long id, String bookName, String author, Integer number, Timestamp ts, boolean flag);
	Page<Book> findAll(Pageable page);
	List<Book> findAllByBookNameLike( String keyword );
	List<Book> findAllByAuthorLike( String keyword );
	List<Book> find(String bookName, String author);
}
