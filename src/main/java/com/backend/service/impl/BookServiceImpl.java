package com.backend.service.impl;


import com.backend.model.Author;
import com.backend.model.Book;
import com.backend.model.Category;
import com.backend.model.dto.BookDto;
import com.backend.model.exceptions.AuthorNotFoundException;
import com.backend.model.exceptions.BookNotFoundException;
import com.backend.model.exceptions.CategoryNotFoundException;
import com.backend.repository.AuthorRepository;
import com.backend.repository.BookRepository;
import com.backend.repository.CategoryRepository;
import com.backend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> save(String name, Long categoryId, Long authorId, int availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException());
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException());
        Book book=new Book(name,category,author,availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Long categoryId, Long authorId, int availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException());
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException());
        Book book=this.findById(id).orElseThrow(()->new BookNotFoundException());
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book=this.findById(id).orElseThrow(()->new BookNotFoundException());
        int availableCopies=book.getAvailableCopies();
        book.setAvailableCopies(availableCopies-1);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException());
        Category category=this.categoryRepository.findById(bookDto.getCategory()).orElseThrow(()->new CategoryNotFoundException());

        Book book=new Book(bookDto.getName(),category,author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException());
        Category category=this.categoryRepository.findById(bookDto.getCategory()).orElseThrow(()->new CategoryNotFoundException());
        Book book=this.findById(id).orElseThrow(()->new BookNotFoundException());
        book.setName(bookDto.getName());
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        Book book=this.findById(id).orElseThrow(()->new BookNotFoundException());
        this.bookRepository.delete(book);
    }
}
