package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

	private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);
	private static final Map<Integer, BigDecimal> DISCOUNTS = Map.of(
			1, BigDecimal.ZERO, 
			2, BigDecimal.valueOf(0.05), 
			3, BigDecimal.valueOf(0.10),
			4, BigDecimal.valueOf(0.20),
			5, BigDecimal.valueOf(0.25));

	public BigDecimal calculate(Basket basket) {
		//Extract distinctBooks from basket and sort in descending order
        List<Integer> distinctBooks = basket.getItems().values().stream()
            .filter(q -> q > 0)
            .sorted(Comparator.reverseOrder())
            .toList();

		return calculateBestPrice(distinctBooks);
	}
	
	 
    private BigDecimal priceForSet(int size) {
    	//Calculate base price eg, size × 50. Apply discount based on set size. Return discounted price
        BigDecimal base =
            BOOK_PRICE.multiply(BigDecimal.valueOf(size));
        return base.subtract(base.multiply(DISCOUNTS.get(size)));
    }
    
    private BigDecimal calculateBestPrice(List<Integer> quantities) {
    	int distinctBooks = quantities.size();
    	BigDecimal minPrice = BigDecimal.valueOf(Double.MAX_VALUE);
    	
        if (quantities.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
    	for (int setSize = 1; setSize <= distinctBooks; setSize++) {
            List<Integer> remaining = new ArrayList<>();

            for (int i = 0; i < quantities.size(); i++) {
                int qty = quantities.get(i);
                if (i < setSize && qty > 0) {
                    qty--;
                }
                if (qty > 0) {
                    remaining.add(qty);
                }
            }

            remaining.sort(Comparator.reverseOrder());

            BigDecimal price =
                priceForSet(setSize).add(calculateBestPrice(remaining));

            minPrice = minPrice.min(price);
        }
    	return minPrice;
    }
}
