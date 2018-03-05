package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.entity.Book;
import com.example.business.service.BookService;

@Controller
public class TopController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		List<Book> books = bookService.findAll();
		mav.addObject("books", books);
		mav.setViewName("top/index");
		
		return mav;
	}
}