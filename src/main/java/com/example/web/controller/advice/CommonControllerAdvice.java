package com.example.web.controller.advice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.business.entity.Book;
import com.example.business.entity.Genre;
import com.example.business.service.BookService;
import com.example.security.LoginUserDetails;
import com.example.web.form.BookForm;

@ControllerAdvice
public class CommonControllerAdvice {
	@Autowired
	private BookService bookService;
	
	@ModelAttribute(name = "books")
	public List<Book> setupBooks() {
		return bookService.findAllByOrderByCreatedAtDesc();
	}
	
	@ModelAttribute(name = "bookForm")
	public BookForm setupBookForm() {
		return new BookForm();
	}
	
	@ModelAttribute(name = "loginUser")
	private LoginUserDetails setLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		return loginUserDetails;
	}
	
	@ModelAttribute(name = "genres")
	private Genre[] setGenres() {
		Genre[] genres = Genre.values();
		return genres;
	}
	
	@ModelAttribute(name = "select")
	private Map<String, String> setupSelect() {
		Map<String, String> select = new LinkedHashMap<>();
		for( Genre genre : Genre.values() ) {
			select.put( genre.getGenre(), genre.getGenre() );
		}
		
		return select;
	}
}
