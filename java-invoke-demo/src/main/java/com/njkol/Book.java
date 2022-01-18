package com.njkol;

public class Book {

	String id;
	String title;

	public Book() {

	}

	public Book(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String formatBook() {
		return id + " > " + title;
	}
}