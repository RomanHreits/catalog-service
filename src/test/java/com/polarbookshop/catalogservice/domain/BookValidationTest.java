package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookValidationTest {
    private static Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Book book = new Book("1234567890", "Title", "Author", 9.9);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book book = new Book("123456789", "Title", "Author", 9.9);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertEquals(1, constraintViolations.size());
        assertTrue(constraintViolations.iterator().next().getMessage().contains("The ISBN format must be valid"));
    }
}
