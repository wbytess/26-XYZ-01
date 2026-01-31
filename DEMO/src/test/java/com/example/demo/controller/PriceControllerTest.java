package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
		mockMvc.perform(post("/api/v1/price").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(content().string("50"));
	}

	@Test
	@DisplayName("two different books get 5 percent discount")
	void twoDifferentBooksGet5PercentDiscount() throws Exception {

		// given
		BasketRequest request = TestRequestBuilder.basketWithBooks(
				new BookSpec(1L, "CLEAN_CODE", BigDecimal.valueOf(50), 1),
				new BookSpec(2L, "CLEAN_CODER", BigDecimal.valueOf(50), 1));

		// when / then
		mockMvc.perform(post("/api/v1/price").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(content().string("95.00"));
	}
}
