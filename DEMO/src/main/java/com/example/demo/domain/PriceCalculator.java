package com.example.demo.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

	private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);

	public BigDecimal calculate(Basket basket) {
		if (basket.getItems().size() == 1) {
			int quantity = basket.getItems().values().iterator().next();

			if (quantity == 1) {
				return BOOK_PRICE;
			}
		}

		return BigDecimal.ZERO;
	}
}
