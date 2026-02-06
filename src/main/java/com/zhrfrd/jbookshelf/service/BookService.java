package com.zhrfrd.jbookshelf.service;

import com.zhrfrd.jbookshelf.dto.BookRequest;
import com.zhrfrd.jbookshelf.dto.BookResponse;
import com.zhrfrd.jbookshelf.dto.BookUpdateRequest;
import com.zhrfrd.jbookshelf.exception.ResourceNotFoundException;
import com.zhrfrd.jbookshelf.model.Book;
import com.zhrfrd.jbookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book create(BookRequest request) {
        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPublishedYear()
        );

        return bookRepository.save(book);
    }

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedYear()
        );
    }

    @Transactional
    public Book patchUpdate(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (request.getTitle() != null) {
            book.updateTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.updateAuthor(request.getAuthor());
        }
        if (request.getIsbn() != null) {
            book.updateIsbn(request.getIsbn());
        }
        if (request.getPublishedYear() != null) {
            book.updatePublishedYear(request.getPublishedYear());
        }

        // No explicit save needed: because we're inside @Transactional,
        // Hibernate will flush changes automatically at commit.
        return book;
    }
}