package com.example.demo.testutil;

import java.math.BigDecimal;

import com.example.demo.dto.BookRequest;

public final class BookTestFactory {

    private BookTestFactory() {}

    public static BookRequest cleanCode() {
        return bookRequest(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
    }

    public static BookRequest cleanCoder() {
        return bookRequest(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
    }

    public static BookRequest cleanArchitecture() {
        return bookRequest(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50));
    }

    public static BookRequest bookRequest(Long id, String name, BigDecimal price) {
        BookRequest book = new BookRequest();
        book.setBookId(id);
        book.setBookName(name);
        book.setPrice(price);
        return book;
    }
}
