package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Book(
        Long bookId,
        String bookName,
        BigDecimal price
) {
    public Book {
        Objects.requireNonNull(bookId, "bookId must not be null");
        Objects.requireNonNull(bookName, "bookName must not be null");
        Objects.requireNonNull(price, "price must not be null");

        if (price.signum() < 0) {
            throw new IllegalArgumentException("price must be non-negative");
        }
    }
}
