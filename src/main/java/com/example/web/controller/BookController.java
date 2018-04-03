package com.example.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.entity.Book;
import com.example.business.entity.Genre;
import com.example.business.service.BookService;
import com.example.security.LoginUserDetails;
import com.example.web.form.BookForm;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	//@GetMapping("/books/insert/")
	@RequestMapping(value = "/books/insert", method = RequestMethod.GET)
	public ModelAndView insertBook(ModelAndView mav, BookForm form) {
		mav.setViewName("book/insert");
		
		return mav;
	}
	
	//@PostMapping("/books/insert/")
	@RequestMapping(value = "/books/insert", method = RequestMethod.POST)
	public ModelAndView insertBook(ModelAndView mav,
								  @Validated BookForm form,
								  BindingResult result,
								  @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		
		if( result.hasErrors() ) {
			return insertBook(mav, form);
		}
		
		Book book = new Book();
		form.setGenre( Genre.fromString( form.getText() ) );
		BeanUtils.copyProperties(form, book);
		//book.setGenre( Genre.fromString( form.getGenre() ) );
		bookService.save(book, loginUserDetails.getUserId());
		mav.setViewName( "redirect:/" );
		return mav;
	}
	
	@GetMapping("/books/search")
	public ModelAndView searchBook(ModelAndView mav, 
								  @RequestParam(defaultValue = "") String bookName, 
								  @RequestParam(defaultValue = "") String author) {
		mav.addObject( "books", bookService.find(bookName, author) );
		mav.setViewName("book/search");
		
		return mav;
	}
	
	@GetMapping("/books/delete")
	public ModelAndView deleteBook(ModelAndView mav, 
								  @RequestParam(defaultValue = "") String bookName, 
								  @RequestParam(defaultValue = "") String author) {
		Map<Long,BookForm> items = new HashMap<>();
		List<Book> books = bookService.find(bookName, author);
		
		for(Book book : books) {
			items.put( book.getId(), new BookForm( book.getBookName(), book.getAuthor(), book.getImageUrl(), book.getNumber(), book.getGenre().toString() ) );
		}
		mav.addObject("items", items);
		mav.setViewName("book/delete");
		return mav;
	}
	
	@PostMapping("/books/delete")
	public ModelAndView deleteBook(ModelAndView mav, 
								  BookForm bookForm, 
								  @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		String[] checkBoxes = bookForm.getInputMultiCheck();
		for(String id_str : checkBoxes) {
			Book book = bookService.findOne( Long.parseLong(id_str) );
			book.setDeleteFlag(true);
			bookService.save(book, loginUserDetails.getUserId());
		}
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@GetMapping("/books/{id}")
	public ModelAndView show(ModelAndView mav, @PathVariable("id") Long id) {
		Book book = bookService.findOne(id);
		mav.addObject("book", book);
		mav.setViewName("book/show");
		
		return mav;
	}
	
	@GetMapping("/books/{id}/edit")
	public ModelAndView editBook(ModelAndView mav, @PathVariable("id") Long id) {
		Book book = bookService.findOne(id);
		mav.addObject("book", book);
		mav.setViewName("book/edit");
		
		return mav;
	}
	
	@PostMapping("/books/{id}/edit")
	public ModelAndView updateBook(ModelAndView mav, 
								  @PathVariable("id") Long id, 
								  Book updateBook,
								  @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		Book book = bookService.findOne(id);
		BeanUtils.copyProperties(updateBook, book);
		bookService.save(book, loginUserDetails.getUserId());
		mav.setViewName("redirect:/");
		
		return mav;
	}
	
	@GetMapping("/books/{id}/delete")
	public ModelAndView deleteBook(ModelAndView mav, @PathVariable("id") Long id) {
		Book book = bookService.findOne(id);
		mav.addObject( "book", book );
		mav.setViewName("book/delete");
		
		return mav;
	}
	
	@PostMapping("/books/{id}/delete")
	public ModelAndView updateBook(ModelAndView mav, 
								  @PathVariable("id") Long id,
								  @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		Book book = bookService.findOne(id);
		book.setDeleteFlag(true);
		bookService.save(book,loginUserDetails.getUserId());
		mav.setViewName( "redirect:/" );
		
		return mav;
	}
	
	@RequestMapping( value = "/books/autocomplete", method = RequestMethod.POST )
	@ResponseBody
	public List<String> autoComplete( @RequestBody Book param) {
		String term = param.getBookName();
		List<Book> books = bookService.find(term, "");
		
		List<String> result = new ArrayList<>();
		for(Book book : books) {
			result.add( book.getBookName() );
		}
		
		
		return result;
	}
}
