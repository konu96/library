package com.example.business.entity;

public enum Genre {
	NOVEL("小説"), NONFICTION("ノンフィクション"), BUSINESS("ビジネス・経済"), COMPUTER("コンピュータ"), SCIENCE("サイエンス"), OTHER("その他");
	
	private String genre;
	
	Genre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public static Genre fromString(String str) {
		Genre[] genres = Genre.values();
        for(Genre genre : genres) {
            if (str.equals(genre.getGenre().toString())){
                return genre;
            }
        }
        return null;
    }
}
