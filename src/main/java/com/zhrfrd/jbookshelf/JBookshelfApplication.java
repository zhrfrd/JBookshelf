package com.zhrfrd.jbookshelf;

import com.zhrfrd.jbookshelf.model.Book;
import com.zhrfrd.jbookshelf.repository.BookRepository;
import com.zhrfrd.jbookshelf.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JBookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBookshelfApplication.class, args);
	}
}
