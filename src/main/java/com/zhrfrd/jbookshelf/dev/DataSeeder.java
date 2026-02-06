package com.zhrfrd.jbookshelf.dev;

import com.github.javafaker.Faker;
import com.zhrfrd.jbookshelf.model.Book;
import com.zhrfrd.jbookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataSeeder {
    private final BookRepository bookRepository;
    private final Faker faker = new Faker();

    public DataSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void seedBooks(int count) {
        // Prevent accidental duplicates
        if (bookRepository.count() > 0) {
            return;
        }

        List<Book> books = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            String title = faker.book().title();
            String author = faker.book().author();
            String isbn = faker.code().isbn13();
            Integer year = faker.number().numberBetween(1950, Year.now().getValue());

            books.add(new Book(title, author, isbn, year));
        }

        bookRepository.saveAll(books);
    }
}