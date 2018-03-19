package com.example.business.entity;

public enum Genre {
	NOVEL("novel"), NONFICTION("non fiction"), BUSINESS("business"), COMPUTER("computer"), SCIENCE("science"), OTHER("other");
	
	private String genre;
	
	Genre(String genre) {
		this.genre = genre;
	}
}
