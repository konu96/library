package com.example.web.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.entity.Book;
import com.example.business.service.BookService;
import com.example.web.form.BookForm;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books/insert/")
	public ModelAndView insertBook(ModelAndView mav, BookForm form) {
		mav.addObject( "bookForm", new BookForm() );
		mav.setViewName("book/insert");
		
		return mav;
	}
	
	@PostMapping("/books/insert/")
	public ModelAndView insertBook(ModelAndView mav,
								  @Validated BookForm form,
								  BindingResult result) {
		
		if( result.hasErrors() ) {
			return insertBook(mav, form);
		}
		
		Book book = new Book();
		BeanUtils.copyProperties(form , book);
		bookService.save(book);
		mav.setViewName( "redirect:/" );
		return mav;
	}
	
	@GetMapping("/books/update")
	public ModelAndView updateBook(ModelAndView mav) {
		mav.addObject( "bookForm", new BookForm() );
		mav.setViewName("book/update");
		
		return mav;
	}
	
	@GetMapping("/books/search")
	public ModelAndView searchBook(ModelAndView mav, @RequestParam(defaultValue="") String bookName, @RequestParam(defaultValue="") String author) {
		if(bookName != "" && author == "") {
			mav.addObject( "books", bookService.findAllByBookNameLike(bookName) );
		} else if(bookName == "" && author != "") {
			mav.addObject( "books", bookService.findAllByAuthorLike(author) );
		} else {
			mav.addObject( "books", bookService.find(bookName, author) );
		}
		
		mav.setViewName("book/search");
		
		return mav;
	}
	
	@GetMapping("/books/delete")
	public ModelAndView deleteBook(ModelAndView mav, @RequestParam(defaultValue="") String bookName, @RequestParam(defaultValue="") String author) {
		if(bookName != "" && author == "") {
			mav.addObject( "books", bookService.findAllByBookNameLike(bookName) );
		} else if(bookName == "" && author != "") {
			mav.addObject( "books", bookService.findAllByAuthorLike(author) );
		} else {
			mav.addObject( "books", bookService.find(bookName, author) );
		}
		mav.addObject( "deleteForm", new BookForm() );
		mav.setViewName("book/delete");
		
		return mav;
	}
	
	@PostMapping("/books/delete")
	public ModelAndView deleteBook(ModelAndView mav, 
								  @Validated BookForm form,
								  BindingResult result) {
		
		if( result.hasErrors() ) {
			return deleteBook(mav, "", "");
		}
		
		Book book = new Book();
		BeanUtils.copyProperties(form , book);
		bookService.save(book);
		mav.setViewName( "redirect:/" );
		
		return mav;
	}
	
	@GetMapping("/books/{id}")
	public ModelAndView show(@PathVariable("id") Long id, ModelAndView mav) {
		Book book = bookService.findOne(id);
		mav.addObject("book", book);
		mav.setViewName("book/show");
		
		return mav;
	}
}
