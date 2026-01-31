package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {

    private Long bookId;
    private String bookName;
    private BigDecimal price;

    public Book(Long bookId, String bookName, BigDecimal price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

	public Long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}
