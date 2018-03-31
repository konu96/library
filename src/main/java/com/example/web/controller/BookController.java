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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.entity.Book;
import com.example.business.service.BookService;
import com.example.security.LoginUserDetails;
import com.example.web.form.BookForm;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books/insert/")
	public ModelAndView insertBook(ModelAndView mav, BookForm form) {
		System.out.println("aaaaaa");
		mav.addObject( "book", form );
		mav.setViewName("book/insert");
		
		return mav;
	}
	
	@PostMapping("/books/insert/")
	public ModelAndView insertBook(ModelAndView mav,
								  @Validated BookForm form,
								  BindingResult result,
								  @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		
		if( result.hasErrors() ) {
			return insertBook(mav, form);
		}
		
		Book book = new Book();
		BeanUtils.copyProperties(form, book);
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
		List<Book> books = books = bookService.find(bookName, author);
		
		for(Book book : books) {
			items.put( book.getId(), new BookForm( book.getBookName(), book.getAuthor(), book.getImageUrl(), book.getNumber(), book.getGenre() ) );
		}
		mav.addObject("items", items);
		mav.addObject("bookForm", new BookForm());
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
}
