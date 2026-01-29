package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceCalculatorTest {

	private final PriceCalculator priceCalculator = new PriceCalculator();

	@Test
	@DisplayName("price of a single book should be 50 EUR")
	void singleBookCosts50() {
		Basket basket = new Basket(Map.of(Book.CLEAN_CODE, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(BigDecimal.valueOf(50), price);
	}
}
