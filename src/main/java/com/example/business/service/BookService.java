package com.example.business.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.business.entity.Book;

public interface BookService {
	Book save(Book book, Long userId);
	Book findOne(Long id);
	Integer updateBook(Long id, String bookName, String author, Integer number, Timestamp ts, boolean flag);
	List<Book> findAll();
	List<Book> findAllByOrderByCreatedAtDesc();
	Page<Book> findAll(Pageable page);
	List<Book> find(String bookName, String author);
}
