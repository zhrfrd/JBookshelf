package com.zhrfrd.jbookshelf;

import com.zhrfrd.jbookshelf.model.Book;
import com.zhrfrd.jbookshelf.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JBookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBookshelfApplication.class, args);
	}

    @Bean
    CommandLineRunner demo(BookRepository bookRepository) {
        return args -> {
            Book book = new Book(
                    "Clean Code",
                    "Robert C. Martin",
                    "9780132350884",
                    2008
            );

            bookRepository.save(book);

            System.out.println("Books in DB: " + bookRepository.count());
        };
    }

}
