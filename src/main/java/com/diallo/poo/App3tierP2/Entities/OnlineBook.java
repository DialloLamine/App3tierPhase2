package com.diallo.poo.App3tierP2.Entities;

import java.util.UUID;

public class OnlineBook extends Book {
	private byte maxPeople;
	private String content;
	
	public OnlineBook(UUID id, String title, String author, short totalPages, String language) {
		super(id, title, author, totalPages, language);
		this.maxPeople = 2;
		this.content = "";
	}

	public byte getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(byte maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "\n\tOnlineBook [maxPeople = " + maxPeople 
				+ "\n\t content = " + content
				+ "\n\t id = " + id 
				+ "\n\t title = " + title
				+ "\n\t author = " + author 
				+ "\n\t totalPages = " + totalPages 
				+ "\n\t loanPeriod = " + loanPeriod 
				+ "\n\t rentalPrice = " + rentalPrice 
				+ "\n\t borrowingDate = " + borrowingDate
				+ "\n\t language = " + language 
				+ "\n\t borrower = " + borrower
				+ "]";
	}
	
}