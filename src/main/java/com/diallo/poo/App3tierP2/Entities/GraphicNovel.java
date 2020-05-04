package com.diallo.poo.App3tierP2.Entities;

import java.util.UUID;

public class GraphicNovel extends Book {
	private String cartoonist;
	private boolean color;
	
	public GraphicNovel(UUID id, String title, String author, short totalPages, String language, String cartoonist) {
		super(id, title, author, totalPages, language);
		this.cartoonist = cartoonist;
		this.color = true;
	}

	public String getCartoonist() {
		return cartoonist;
	}

	public void setCartoonist(String cartoonist) {
		this.cartoonist = cartoonist;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "\n GraphicNovel [cartoonist = " + cartoonist 
				+ "\n\t color = " + color 
				+ "\n\t id = " + id 
				+ "\n\t title = " + title
				+ "\n\t author = " + author 
				+ "\n\t totalPages = " + totalPages 
				+ "\n\t loanPeriod=" + loanPeriod 
				+ "\n\t rentalPrice = " + rentalPrice 
				+ "\n\t borrowingDate = " + borrowingDate 
				+ "\n\t language = " + language 
				+ "\n\t borrower = " + borrower
				+ "]";
	}
	
}