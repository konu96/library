package com.example.web.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.business.entity.Book;
import com.example.business.service.BookService;

@ControllerAdvice
public class CommonControllerAdvice {
	@Autowired
	private BookService bookService;
	
	@ModelAttribute(name = "books")
	public List<Book> setupBooks() {
		return bookService.findAll();
	}

}
