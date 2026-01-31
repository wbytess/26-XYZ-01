package com.example.demo.domain.dto;

public class BasketItemRequest {

	private BookRequest book;
    private int quantity;
    
	public BookRequest getBook() {
		return book;
	}
	public void setBook(BookRequest book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
