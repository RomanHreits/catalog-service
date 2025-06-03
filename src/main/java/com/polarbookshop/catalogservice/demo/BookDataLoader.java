package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import com.polarbookshop.catalogservice.domain.BookRepositoryLocal;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
/*@ConditionalOnProperty(name = "polar.testdata.enabled", havingValue = "true") - another way to conditionally
    load this component "polar.testdata.enabled" yml property
 */
public class BookDataLoader {
    private final BookRepositoryLocal bookRepositoryLocal;
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepositoryLocal bookRepositoryLocal, BookRepository bookRepository) {
        this.bookRepositoryLocal = bookRepositoryLocal;
        this.bookRepository = bookRepository;
    }

    /**
     * Loads test data into the book repository when the application is ready.
     * This method is only active when the 'testdata' profile is active.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();

        Book book = Book.of("1234567890", "Northern Lights", "Lyra Silverstar", 9.9);
        Book book2 = Book.of("1234567891", "Polar Journey", "Iorek Palarson", 12.9);

        bookRepository.saveAll(List.of(book, book2));

        bookRepositoryLocal.save(book);
        bookRepositoryLocal.save(book2);
    }
}
