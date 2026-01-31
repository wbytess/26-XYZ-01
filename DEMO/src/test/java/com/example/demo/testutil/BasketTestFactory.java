package com.example.demo.testutil;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.BasketItemRequest;
import com.example.demo.dto.BasketRequest;
import com.example.demo.dto.BookRequest;

public final class BasketTestFactory {

    private BasketTestFactory() {}

    public static BasketRequest basketWith(BookRequest book, int quantity) {
        BasketItemRequest item = new BasketItemRequest();
        item.setBook(book);
        item.setQuantity(quantity);

        BasketRequest basket = new BasketRequest();
        basket.getItems().add(item);
        return basket;
    }

    public static BasketRequest basketWithItems(BasketItemRequest... items) {
        BasketRequest basket = new BasketRequest();
        basket.getItems().addAll(new ArrayList<>(List.of(items)));
        return basket;
    }

    public static BasketItemRequest item(BookRequest book, int quantity) {
        BasketItemRequest item = new BasketItemRequest();
        item.setBook(book);
        item.setQuantity(quantity);
        return item;
    }
}
