package com.example.web.form;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.example.business.entity.Genre;

public class BookForm implements Serializable {
	@NotBlank
	private String bookName;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String imageUrl;
	
	@NotBlank
	private String publisher;
	
	@Min(1)
	private Integer number;
	
	@NotBlank
	private String text;
	
	private Genre genre;
	
	private String[] inputMultiCheck;
	
	public BookForm(String bookName, String author, String imageUrl, Integer number, String text) {
		this.bookName = bookName;
		this.author   = author;
		this.imageUrl = imageUrl;
		this.number   = number;
		this.genre    = Genre.fromString(text);
	}
	
	public BookForm() {

	}

	public String getBookName() {
		return this.bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Integer getNumber() {
		return this.number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Genre getGenre() {
		return this.genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public String[] getInputMultiCheck() {
	     return inputMultiCheck;
	}

	public void setInputMultiCheck(String[] inputMultiCheck) {
	     this.inputMultiCheck = inputMultiCheck;
	}
}
