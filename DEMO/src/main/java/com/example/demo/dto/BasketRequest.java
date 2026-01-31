package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

public class BasketRequest {

    @Valid
    private List<BasketItemRequest> items = new ArrayList<>();

	public List<BasketItemRequest> getItems() {
		if (items == null) {
	        items = new ArrayList<>();
	    }
	    return items;
	}
	 
}
