package com.backend.dataholder;


import com.backend.model.Author;
import com.backend.model.Book;
import com.backend.model.Category;
import com.backend.model.Country;
import com.backend.repository.AuthorRepository;
import com.backend.repository.BookRepository;
import com.backend.repository.CategoryRepository;
import com.backend.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final CategoryRepository categoryRepository;

    public static List<Book> books=new ArrayList<>();
    public static List<Category> categories=new ArrayList<>();
    public static List<Author> authors=new ArrayList<>();
    public static List<Country> countries=new ArrayList<>();

    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init(){

        categories.add(new Category("NOVEL"));
        categories.add(new Category("THRILLER"));
        categories.add(new Category("HISTORY"));
        categories.add(new Category("FANTASY"));
        categories.add(new Category("BIOGRAPHY"));
        categories.add(new Category("CLASSICS"));
        categories.add(new Category("DRAMA"));

        categoryRepository.saveAll(categories);

        countries.add(new Country("United States","North America"));
        countries.add(new Country("Spain","Europe"));
        countries.add(new Country("China","Asia"));

        countryRepository.saveAll(countries);

        authors.add(new Author("Diana","Gabaldon",countries.get(0)));
        authors.add(new Author("Yiyun","Li",countries.get(1)));
        authors.add(new Author("Laura","Esquivel",countries.get(2)));

        authorRepository.saveAll(authors);

        books.add(new Book("Outlander",categories.get(0),authors.get(0),4));
        books.add(new Book("The Vagrants",categories.get(2),authors.get(1),2));
        books.add(new Book("Like Water for Chocolate",categories.get(3),authors.get(2),5));

        bookRepository.saveAll(books);
    }
}
