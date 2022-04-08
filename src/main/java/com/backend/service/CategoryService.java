package com.backend.service;

import com.backend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    List<Category> findAll();
}
