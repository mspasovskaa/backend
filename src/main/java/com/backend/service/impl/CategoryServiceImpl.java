package com.backend.service.impl;

import com.backend.model.Category;
import com.backend.model.exceptions.CategoryNotFoundException;
import com.backend.repository.CategoryRepository;
import com.backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category=this.categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException());
        return Optional.of(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }
}
