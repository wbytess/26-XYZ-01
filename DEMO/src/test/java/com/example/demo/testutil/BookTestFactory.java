package com.example.demo.testutil;

import java.math.BigDecimal;

import com.example.demo.dto.BookRequest;

public final class BookTestFactory {

    private BookTestFactory() {}

    public static BookRequest cleanCode() {
        return book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
    }

    public static BookRequest cleanCoder() {
        return book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
    }

    public static BookRequest cleanArchitecture() {
        return book(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50));
    }

    public static BookRequest book(Long id, String name, BigDecimal price) {
        BookRequest book = new BookRequest();
        book.setBookId(id);
        book.setBookName(name);
        book.setPrice(price);
        return book;
    }
}
