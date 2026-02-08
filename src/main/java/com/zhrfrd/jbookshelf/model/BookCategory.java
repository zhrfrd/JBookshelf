package com.zhrfrd.jbookshelf.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "book_categories",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"book_id", "category_id"})}
)
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    protected BookCategory() {}

    public BookCategory(Book book, Category category) {
        this.book = book;
        this.category = category;
    }
}
