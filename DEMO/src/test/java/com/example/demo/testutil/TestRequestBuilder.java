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
	    BasketRequest request = new BasketRequest();

	    for (BookSpec spec : specs) {
	        BookRequest bookRequest = BookTestFactory.bookRequest(
	                spec.bookId(),
	                spec.bookName(),
	                spec.price()
	        );

	        BasketItemRequest item = new BasketItemRequest();
	        item.setBook(bookRequest);
	        item.setQuantity(spec.quantity());

	        request.getItems().add(item);
	    }

	    return request;
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
