package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Basket;
import com.example.demo.domain.PriceCalculator;
import com.example.demo.dto.BasketItemRequest;
import com.example.demo.dto.BasketRequest;
import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PriceController {
	
	private PriceCalculator priceCalculator;
	
	public PriceController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

	@PostMapping("/v1/price")
	public ResponseEntity<BigDecimal> calculatePrice(@Valid @RequestBody BasketRequest request) {
	        Basket basket = toBasket(request);
	        BigDecimal price = priceCalculator.calculate(basket);
	        return ResponseEntity.ok(price);
	}
	
	public Basket toBasket(BasketRequest request) {
	    Map<Book, Integer> items = new HashMap<>();
	    if (request.getItems() != null) {
	        for (BasketItemRequest item : request.getItems()) {
	            BookRequest br = item.getBook();
	            Book book = new Book(br.getBookId(), br.getBookName(), br.getPrice());
	            items.put(book, item.getQuantity());
	        }
	    }
	    return new Basket(items);
	}
}
