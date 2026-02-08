package com.zhrfrd.jbookshelf.repository;

import com.zhrfrd.jbookshelf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}