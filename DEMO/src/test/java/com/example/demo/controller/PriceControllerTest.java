package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.BasketRequest;
import com.example.demo.testutil.TestRequestBuilder;
import com.example.demo.testutil.TestRequestBuilder.BookSpec;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("price of a single book should be 50 EUR")
	void priceOfSingleBookShouldBe50() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithCleanCode(1);

		// when / then
		assertPrice(request, BigDecimal.valueOf(50));
	}

	@Test
	@DisplayName("two different books get 5 percent discount")
	void twoDifferentBooksGet5PercentDiscount() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 1),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(95));
	}
	
	@Test
	@DisplayName("three different books get 10 percent discount")
	void threeDifferentBooksGet10PercentDiscount() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 1),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 1),
				new BookSpec(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(135));
		
	}
	
	@Test
	@DisplayName("four different books get 20 percent discount")
	void fourDifferentBooksGet20PercentDiscount() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 1),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 1),
				new BookSpec(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50), 1),
				new BookSpec(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(160));
	}
	
	@Test
	@DisplayName("five different books get 25 percent discount")
	void fiveDifferentBooksGet25PercentDiscount() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 1),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 1),
				new BookSpec(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50), 1),
				new BookSpec(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50), 1),
				new BookSpec(5L, "LEGACY_CODE", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(187.50));
	}
	
	@Test
	@DisplayName("assignment example basket should cost 320 EUR.")
	void assignmentExample() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 2),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 2),
				new BookSpec(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50), 2),
				new BookSpec(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50), 1),
				new BookSpec(5L, "LEGACY_CODE", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(320));
	}
	
	@Test
	@DisplayName("basket should cost 455 EUR.")
	void basketExampleShouldCost455() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 3),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 3),
				new BookSpec(3L, "CLEAN_ARCHITECTURE", BigDecimal.valueOf(50), 3),
				new BookSpec(4L, "TDD_BY_EXAMPLE", BigDecimal.valueOf(50), 1),
				new BookSpec(5L, "LEGACY_CODE", BigDecimal.valueOf(50), 1));

		// when / then
		assertPrice(request, BigDecimal.valueOf(455));
	}
	
	@Test
	@DisplayName("bookId must not be null")
    void shouldReturnBadRequestForBookWithNullId() throws Exception {
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(null, "CLEAN_CODE", BigDecimal.valueOf(50), 1));

		// when / then
        mockMvc.perform(post("/api/v1/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
	
	private void assertPrice(BasketRequest request, BigDecimal expected) throws Exception {
	    mockMvc.perform(
	            post("/api/v1/price")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request))
	        )
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$").value(expected.doubleValue()));
	}

}
