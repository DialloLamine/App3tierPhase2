package com.diallo.poo.App3tierP2.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Person {
	protected UUID id;
	private String name;
	private byte maxBooks;
	private LocalDate registrationDate;
	private ArrayList<Book> books;
	
	public Person(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.maxBooks = 3;
		this.registrationDate = LocalDate.now();
		this.books = new ArrayList<Book>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getMaxBooks() {
		return maxBooks;
	}

	public void setMaxBooks(byte maxBooks) {
		this.maxBooks = maxBooks;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public UUID getId() {
		return id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		final int maxLen = 3;
		return "\n Person [id = " + id 
				+ "\n\t name = " + name
				+ "\n\t maxBooks = " + maxBooks
				+ "\n\t registrationDate = " + registrationDate 
				+ "\n\t books = "
				+ (books != null ? books.subList(0, Math.min(books.size(), maxLen)) : null) + "]";
	}

	public void borrows(Book book) {
		this.books.add(book);
		book.setBorrower(this);
		book.borrowingDate = LocalDate.now();
	}

	public void returns(Book book) {
		this.books.remove(book);
		book.setBorrower(null);
		book.borrowingDate = null;
	}
	
}


