package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;

@Service
public class PriceCalculator {

	private static final Map<Integer, BigDecimal> DISCOUNTS = Map.of(
			1, BigDecimal.ZERO, 
			2, BigDecimal.valueOf(0.05), 
			3, BigDecimal.valueOf(0.10),
			4, BigDecimal.valueOf(0.20),
			5, BigDecimal.valueOf(0.25));

	public BigDecimal calculate(Basket basket) {
		// Extract book prices and quantities
        List<Book> books = new ArrayList<>(basket.getItems().keySet());
        
		//Extract distinctBooks from basket and sort in descending order
        List<Integer> distinctBooks = basket.getItems().values().stream()
            .filter(q -> q > 0)
            .sorted(Comparator.reverseOrder())
            .toList();

		return calculateBestPrice(books, distinctBooks);
	}
    
    private BigDecimal calculateBestPrice(List<Book> books, List<Integer> quantities) {
    	int distinctBooks = quantities.size();
    	BigDecimal minPrice = BigDecimal.valueOf(Double.MAX_VALUE);
    	
        if (quantities.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
    	for (int setSize = 1; setSize <= distinctBooks; setSize++) {
            List<Integer> remaining = new ArrayList<>();
            BigDecimal setPrice = BigDecimal.ZERO;
            for (int i = 0; i < quantities.size(); i++) {
                int qty = quantities.get(i);
                if (i < setSize && qty > 0) {
                    qty--;
                    setPrice = setPrice.add(books.get(i).price()); // sum price of this set
                }
                if (qty > 0) {
                    remaining.add(qty);
                }
            }

            remaining.sort(Comparator.reverseOrder());

            // Apply discount to this set
            BigDecimal discount = DISCOUNTS.getOrDefault(setSize, BigDecimal.ZERO);
            BigDecimal priceAfterDiscount = setPrice.multiply(BigDecimal.ONE.subtract(discount));

            // Recursively calculate remaining
            BigDecimal totalPrice = priceAfterDiscount.add(calculateBestPrice(books, remaining));

            minPrice = minPrice.min(totalPrice);
        }
    	return minPrice;
    }
}
