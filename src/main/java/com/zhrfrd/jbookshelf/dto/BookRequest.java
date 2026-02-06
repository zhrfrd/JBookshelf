package com.zhrfrd.jbookshelf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookRequest {
    @NotBlank(message = "Title must not be empty")
    private String title;
    @NotBlank(message = "Author must not be empty")
    private String author;
    @Size(min = 10, max = 13, message = "ISBN must be 10–13 characters")
    private String isbn;
    @NotNull(message = "Published year is required")
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
