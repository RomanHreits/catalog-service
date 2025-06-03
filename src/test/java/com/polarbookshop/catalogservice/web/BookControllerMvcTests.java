package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.controller.BookController;
import com.polarbookshop.catalogservice.domain.BookService;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@TestPropertySource(properties = "spring.cloud.config.enabled=false")
public class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistsThenReturnNotFound() throws Exception {
        String isbn = "1234567890";
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books" + isbn)).andExpect(status().isNotFound());
    }
}
