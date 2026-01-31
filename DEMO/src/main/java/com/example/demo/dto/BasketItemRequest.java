package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class BasketItemRequest {

    @Valid
    @NotNull
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
