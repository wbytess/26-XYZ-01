package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.model.Book;

public class PriceCalculatorTest {

	private final PriceCalculator priceCalculator = new PriceCalculator();

	@Test
	@DisplayName("price of a single book should be 50 EUR")
	void priceOfSingleBookIs50() {
		Book cleanCode = new Book(1L,"CLEAN_CODE",BigDecimal.valueOf(50));
		Basket basket = new Basket(Map.of(cleanCode, 1));

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(BigDecimal.valueOf(50), price);
	}

	@Test
	@DisplayName("two different books get 5 percent discount")
	void twoDifferentBooksGet5PercentDiscount() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 1,
			        cleanCoder, 1
			    )
			);

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(95)));
	}

	@Test
	@DisplayName("three different books get 10 percent discount")
	void threeDifferentBooksGet10PercentDiscount() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book cleanArchitecture = new Book(3L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 1,
			        cleanCoder, 1,
			        cleanArchitecture, 1
			    )
			);

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(135)));
	}

	@Test
	@DisplayName("four different books get 20 percent discount")
	void fourDifferentBooksGet20PercentDiscount() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book cleanArchitecture = new Book(3L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book tddByExample = new Book(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 1,
			        cleanCoder, 1,
			        cleanArchitecture, 1,
			        tddByExample, 1
			    )
			);

		BigDecimal price = priceCalculator.calculate(basket);

		assertEquals(0, price.compareTo(BigDecimal.valueOf(160)));
	}
	
	@Test
	@DisplayName("five different books get 25 percent discount")
	void fiveDifferentBooksGet25PercentDiscount() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book cleanArchitecture = new Book(3L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book tddByExample = new Book(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50));
		Book legacyCode = new Book(5L, "LEGACY_CODE", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 1,
			        cleanCoder, 1,
			        cleanArchitecture, 1,
			        tddByExample, 1,
			        legacyCode, 1
			    )
			);

	    BigDecimal price = priceCalculator.calculate(basket);

	    assertEquals(0, price.compareTo(BigDecimal.valueOf(187.5)));
	}
	
	@Test
	@DisplayName("assignment example basket should cost 320 EUR.")
    void assignmentExample() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book cleanArchitecture = new Book(3L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book tddByExample = new Book(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50));
		Book legacyCode = new Book(5L, "LEGACY_CODE", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 2,
			        cleanCoder, 2,
			        cleanArchitecture, 2,
			        tddByExample, 1,
			        legacyCode, 1
			    )
			);
        
        BigDecimal price = priceCalculator.calculate(basket);
        assertEquals(0, price.compareTo(BigDecimal.valueOf(320)));
    }
	
	@Test
	@DisplayName("basket should cost 455 EUR.")
    void basketExampleShouldCost455() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		Book cleanCoder = new Book(2L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book cleanArchitecture = new Book(3L, "CLEAN_CODER", BigDecimal.valueOf(50));
		Book tddByExample = new Book(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50));
		Book legacyCode = new Book(5L, "LEGACY_CODE", BigDecimal.valueOf(50));
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 3,
			        cleanCoder, 3,
			        cleanArchitecture, 3,
			        tddByExample, 1,
			        legacyCode, 1
			    )
			);
        
        BigDecimal price = priceCalculator.calculate(basket);
        assertEquals(0, price.compareTo(BigDecimal.valueOf(455)));
    }
	
	@Test
	@DisplayName("no discount for 5 copies of same book (5 * base price).")
    void basketExampleShouldCost250() {
		Book cleanCode = new Book(1L, "CLEAN_CODE", BigDecimal.valueOf(50));
		
		Basket basket = new Basket(
			    Map.of(
			        cleanCode, 5
			    )
			);
        
        BigDecimal price = priceCalculator.calculate(basket);
        assertEquals(0, price.compareTo(BigDecimal.valueOf(250)));
    }
}
