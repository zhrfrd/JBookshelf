package com.zhrfrd.jbookshelf.controller;

import com.zhrfrd.jbookshelf.dto.BookRequest;
import com.zhrfrd.jbookshelf.dto.BookResponse;
import com.zhrfrd.jbookshelf.dto.BookUpdateRequest;
import com.zhrfrd.jbookshelf.exception.ResourceNotFoundException;
import com.zhrfrd.jbookshelf.model.Book;
import com.zhrfrd.jbookshelf.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookService.create(bookRequest);
        return bookService.create(book);
    }

    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll()
                .stream()
                .map(bookService::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id) {
        Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookService.toResponse(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public BookResponse patchUpdate(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest request) {
        Book updated = bookService.patchUpdate(id, request);
        return bookService.toResponse(updated);
    }
}
