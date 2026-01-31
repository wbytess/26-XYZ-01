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

@RestController
@RequestMapping("/api")
public class PriceController {
	
	private PriceCalculator priceCalculator;
	
	public PriceController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

	@PostMapping("/v1/price")
	public ResponseEntity<BigDecimal> calculatePrice(@RequestBody BasketRequest request) {
	    try {
	        Basket basket = toBasket(request);
	        BigDecimal price = priceCalculator.calculate(basket);
	        return ResponseEntity.ok(price);
	    } catch (Exception e) {
	        e.printStackTrace(); // log the exception
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	public Basket toBasket(BasketRequest request) {
	    Map<Book, Integer> items = new HashMap<>();
	    if (request.getItems() != null) {
	        for (BasketItemRequest item : request.getItems()) {
	            BookRequest br = item.getBook();
	            Book book = new Book(br.getBookId(), br.getBookName(), br.getPrice());
	            items.merge(book, item.getQuantity(), Integer::sum);
	        }
	    }
	    return new Basket(items);
	}
}
