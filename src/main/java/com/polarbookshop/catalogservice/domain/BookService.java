package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepositoryLocal bookRepositoryLocal;
    private final BookRepository bookRepository;

    public BookService(BookRepositoryLocal bookRepositoryLocal, BookRepository bookRepository) {
        this.bookRepositoryLocal = bookRepositoryLocal;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn).map(existingBook -> {
            Book bookToUpdate = new Book(existingBook.id(), existingBook.version(), existingBook.isbn(), book.title(),
                    book.author(), book.price(), existingBook.publisher(), existingBook.createdDate(), existingBook.lastModifiedDate());
            return bookRepository.save(bookToUpdate);
        }).orElseGet(() -> addBookToCatalog(book));
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepositoryLocal.deleteByIsbn(isbn);
    }
}
