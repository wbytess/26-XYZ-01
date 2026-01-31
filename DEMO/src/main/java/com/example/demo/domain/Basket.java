package com.example.demo.domain;

import java.util.Map;

import com.example.demo.domain.model.Book;

public class Basket {

	private final Map<Book, Integer> items;

	public Basket(Map<Book, Integer> items) {
		this.items = Map.copyOf(items);
	}

	public Map<Book, Integer> getItems() {
		return items;
	}
}
