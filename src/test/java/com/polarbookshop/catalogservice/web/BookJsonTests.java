package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class BookJsonTests {
    @Autowired private JacksonTester<Book> json;

    @Test
    void testSerializeBook() throws Exception {
        Book book = new Book("1234567890", "Title", "Author", 9.9);
        JsonContent<Book> jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo("1234567890");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo("Title");
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo("Author");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(9.9);
    }

    @Test
    void testDeserializeBook() throws Exception {
        String jsonContent = "{\"isbn\":\"1234567890\",\"title\":\"Title\",\"author\":\"Author\",\"price\":9.9}";
        Book book = json.parseObject(jsonContent);
        assertThat(book).usingRecursiveComparison().isEqualTo(new Book("1234567890", "Title", "Author", 9.9));
    }
}
