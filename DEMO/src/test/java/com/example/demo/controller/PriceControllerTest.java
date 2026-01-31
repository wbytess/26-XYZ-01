package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.BasketItemRequest;
import com.example.demo.dto.BasketRequest;
import com.example.demo.dto.BookRequest;
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
        BookRequest book = new BookRequest();
        book.setBookId(1L);
        book.setBookName("CLEAN_CODE");
        book.setPrice(BigDecimal.valueOf(50));

        BasketItemRequest item = new BasketItemRequest();
        item.setBook(book);
        item.setQuantity(1);

        BasketRequest request = new BasketRequest();
        request.getItems().addAll(List.of(item));

        // when / then
        mockMvc.perform(post("/api/v1/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().string("50"));
    }
}
