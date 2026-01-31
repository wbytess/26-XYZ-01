package com.example.demo.testutil;

import java.math.BigDecimal;

import com.example.demo.dto.BasketItemRequest;
import com.example.demo.dto.BasketRequest;
import com.example.demo.dto.BookRequest;

public class TestRequestBuilder {

	private TestRequestBuilder() {}

    /**
     * Build a BasketRequest using book specs.
     */
    public static BasketRequest basketWithBooks(BookSpec... specs) {
        BasketItemRequest[] items = new BasketItemRequest[specs.length];

        for (int i = 0; i < specs.length; i++) {
            BookSpec spec = specs[i];

            BookRequest book = BookTestFactory.book(
                spec.bookId(),
                spec.bookName(),
                spec.price()
            );

            items[i] = BasketTestFactory.item(book, spec.quantity());
        }

        return BasketTestFactory.basketWithItems(items);
    }

    /**
     * method for common test cases.
     */
    public static BasketRequest basketWithCleanCode(int quantity) {
        return BasketTestFactory.basketWith(
            BookTestFactory.cleanCode(),
            quantity
        );
    }

    /**
     * for test setup.
     */
    public record BookSpec(
        Long bookId,
        String bookName,
        BigDecimal price,
        int quantity
    ) {}
}
