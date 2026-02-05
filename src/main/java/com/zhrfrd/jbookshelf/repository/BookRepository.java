package com.zhrfrd.jbookshelf.repository;

import com.zhrfrd.jbookshelf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
