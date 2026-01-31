package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class BasketRequest {

	 private List<BasketItemRequest> items;

	public List<BasketItemRequest> getItems() {
		if (items == null) {
	        items = new ArrayList<>();
	    }
	    return items;
	}
	 
}
