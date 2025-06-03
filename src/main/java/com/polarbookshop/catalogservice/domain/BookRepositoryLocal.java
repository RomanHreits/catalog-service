package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

public interface BookRepositoryLocal {
    Iterable<Book> findAll();
    Optional<Book> findBookByIsbn(String isbn);
    Book save(Book book);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
