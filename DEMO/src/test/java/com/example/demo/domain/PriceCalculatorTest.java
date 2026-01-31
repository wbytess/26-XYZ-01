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
	void priceOfSingleBookIs50() {
		Basket basket = new Basket(Map.of(Book.CLEAN_CODE, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(BigDecimal.valueOf(50), price);
	}

	@Test
	@DisplayName("two different books get 5 percent discount")
	void twoDifferentBooksGet5PercentDiscount() {
		Basket basket = new Basket(Map.of(Book.CLEAN_CODE, 1, Book.CLEAN_CODER, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(95)));
	}

	@Test
	@DisplayName("three different books get 10 percent discount")
	void threeDifferentBooksGet10PercentDiscount() {
		Basket basket = new Basket(Map.of(Book.CLEAN_CODE, 1, Book.CLEAN_CODER, 1, Book.CLEAN_ARCHITECTURE, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(135)));
	}

	@Test
	@DisplayName("four different books get 20 percent discount")
	void fourDifferentBooksGet20PercentDiscount() {
		Basket basket = new Basket(
				Map.of(Book.CLEAN_CODE, 1, Book.CLEAN_CODER, 1, Book.CLEAN_ARCHITECTURE, 1, Book.TDD_BY_EXAMPLE, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(160)));
	}
	
	@Test
	@DisplayName("five different books get 25 percent discount")
	void fiveDifferentBooksGet25PercentDiscount() {
	    Basket basket = new Basket(Map.of(
	        Book.CLEAN_CODE, 1,
	        Book.CLEAN_CODER, 1,
	        Book.CLEAN_ARCHITECTURE, 1,
	        Book.TDD_BY_EXAMPLE, 1,
	        Book.LEGACY_CODE, 1
	    ));

	    BigDecimal price = priceCalculator.calculate(basket);

	    assertEquals(0, price.compareTo(BigDecimal.valueOf(187.5)));
	}
	
	@Test
	@DisplayName("assignment example basket should cost 320 EUR.")
    void assignmentExample() {
        Basket basket = new Basket(Map.of(
            Book.CLEAN_CODE, 2,
            Book.CLEAN_CODER, 2,
            Book.CLEAN_ARCHITECTURE, 2,
            Book.TDD_BY_EXAMPLE, 1,
            Book.LEGACY_CODE, 1
        ));
        
        BigDecimal price = priceCalculator.calculate(basket);
        assertEquals(0, price.compareTo(BigDecimal.valueOf(320)));
    }
}
