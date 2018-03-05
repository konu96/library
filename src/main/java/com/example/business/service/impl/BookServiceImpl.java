package com.example.business.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.business.entity.Book;
import com.example.business.repository.BookRepository;
import com.example.business.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}
	
	@Override
	public Integer updateBook(Long id, String bookName, String author, Integer number, Timestamp ts, boolean flag) {
		return bookRepository.updateBook(id, bookName, author, number, ts, flag);
	}
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Override
	public Page<Book> findAll(Pageable page) {
		return bookRepository.findAll(page);
	}
	
	@Override
	public List<Book> findAllByBookNameLike(String keyword) {
		return bookRepository.findAllByBookName(keyword);
	}
	
	@Override
	public List<Book> findAllByAuthorLike(String keyword) {
		return bookRepository.findAllByAuthor(keyword);
	}
	
	@Override
	public List<Book> find(String bookName, String author) {
		return bookRepository.find(bookName, author);
	}
}
