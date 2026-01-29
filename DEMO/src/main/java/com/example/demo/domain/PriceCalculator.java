package com.example.demo.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

	private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);
	private static final BigDecimal FIVE_PERCENT_DISCOUNT = BigDecimal.valueOf(0.05);

	public BigDecimal calculate(Basket basket) {
		int distinctBooks = basket.getItems().size();

		// single book
		if (distinctBooks == 1) {
			return BOOK_PRICE;
		}

		// two different books 5% discount
		if (distinctBooks == 2) {
			BigDecimal total = BOOK_PRICE.multiply(BigDecimal.valueOf(2));
			BigDecimal discount = total.multiply(FIVE_PERCENT_DISCOUNT);
			return total.subtract(discount);
		}

		return BigDecimal.ZERO;
	}
}
