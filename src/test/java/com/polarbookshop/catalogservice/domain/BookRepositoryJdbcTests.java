package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.cloud.config.enabled=false") // in such a way, we can assign a new value to the property
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenExisting() {
        String isbn = "1234567894";
        Book book = Book.of(isbn, "Test Book", "Test Author", 19.99, "Test Publisher");
        jdbcAggregateTemplate.insert(book);

        Optional<Book> actual = bookRepository.findByIsbn(isbn);
        assertThat(actual).isPresent();
        assertThat(actual.get().isbn()).isEqualTo(isbn);
        assertEquals(1, actual.get().version());
        assertNotNull(actual.get().createdDate());
        assertNotNull(actual.get().lastModifiedDate());
        assertNotNull(actual.get().publisher());
    }
}
