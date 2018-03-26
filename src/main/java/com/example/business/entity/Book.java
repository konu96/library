package com.example.business.entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
@Entity
@Table(name = "books")
@Where(clause = " delete_flag = false")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String bookName;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private String imageUrl;
	
	@Column(nullable = false)
	private String publisher;
	
	@Column(nullable = false)
	private Integer number;  // 本の冊数
	
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(nullable = false)
	private Timestamp updatedAt;
	
	@Column(nullable = false)
	private boolean deleteFlag;
	
	@Column(nullable = false)
	private Genre genre;
	
	@OneToOne
	private User user;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public boolean getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp((new Date()).getTime());
        this.createdAt = ts;
        this.updatedAt = ts;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp((new Date()).getTime());
    }
    
    public Genre getGenre() {
    		return this.genre;
    }
    
    public void setGenre(Genre genre) {
    		this.genre = genre;
    }
    
    public User getUser() {
    		return this.user;
    }
    
    public void setUser(User user) {
    		this.user = user;
    }
}
