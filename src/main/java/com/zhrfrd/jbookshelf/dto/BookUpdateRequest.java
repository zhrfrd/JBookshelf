package com.zhrfrd.jbookshelf.dto;

import jakarta.validation.constraints.Size;


public class BookUpdateRequest {
    private String title;
    private String author;
    @Size(min = 10, max = 13, message = "ISBN must be 10–13 characters")
    private String isbn;
    private Integer publishedYear;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }
}