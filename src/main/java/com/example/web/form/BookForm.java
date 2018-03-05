package com.example.web.form;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class BookForm implements Serializable {
	@NotBlank
	private String bookName;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String imageUrl;
	
	@Min(1)
	private Integer number;
	
	private String[] inputMultiCheck;
	
	public BookForm(String bookName, String author, String imageUrl, Integer number) {
		this.bookName = bookName;
		this.author   = author;
		this.imageUrl = imageUrl;
		this.number   = number;
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
	
	public Integer getNumber() {
		return this.number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String[] getInputMultiCheck() {
	     return inputMultiCheck;
	}

	public void setInputMultiCheck(String[] inputMultiCheck) {
	     this.inputMultiCheck = inputMultiCheck;
	}
}
