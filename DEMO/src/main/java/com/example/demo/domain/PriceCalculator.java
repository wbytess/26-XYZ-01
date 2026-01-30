package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

	private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);
	private static final Map<Integer, BigDecimal> DISCOUNTS = Map.of(
			1, BigDecimal.ZERO, 
			2, BigDecimal.valueOf(0.05), 
			3, BigDecimal.valueOf(0.10),
			4, BigDecimal.valueOf(0.20));

	public BigDecimal calculate(Basket basket) {
		int distinctBooks = basket.getItems().size();

		BigDecimal discount = DISCOUNTS.getOrDefault(distinctBooks, BigDecimal.ZERO);

		BigDecimal total = BOOK_PRICE.multiply(BigDecimal.valueOf(distinctBooks));

		return total.subtract(total.multiply(discount));
	}
}
