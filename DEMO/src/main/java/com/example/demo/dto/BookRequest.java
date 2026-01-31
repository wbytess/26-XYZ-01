package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class BookRequest {

    @NotNull(message = "bookId must not be null")
    private Long bookId;

    @NotNull(message = "bookName must not be null")
    private String bookName;

    @NotNull(message = "price must not be null")
    private BigDecimal price;
    
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
    
}
