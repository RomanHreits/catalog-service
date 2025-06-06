package com.polarbookshop.catalogservice.persistance;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepositoryLocal;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepositoryLocal implements BookRepositoryLocal {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();
    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return books.containsKey(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.get(isbn) != null;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}
