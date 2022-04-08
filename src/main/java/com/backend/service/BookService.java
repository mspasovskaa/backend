package com.backend.service;

import com.backend.model.Book;
import com.backend.model.dto.BookDto;


import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Optional<Book> save(String name, Long categoryId, Long authorId, int availableCopies);
    Optional<Book> edit(Long id, String name, Long categoryId, Long authorId, int availableCopies);
    Optional<Book> markAsTaken(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    void deleteById(Long id);
}
