package com.finki.emtlab.config;

import com.finki.emtlab.model.Author;
import com.finki.emtlab.model.Book;
import com.finki.emtlab.model.Category;
import com.finki.emtlab.model.Country;
import com.finki.emtlab.repository.AuthorRepository;
import com.finki.emtlab.repository.BookRepository;
import com.finki.emtlab.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DbSetup {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;

    public DbSetup(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    @Transactional
    public void setup(){
        Country c = new Country();
        c.setContinent("eu");
        c.setName("mk");
        countryRepository.save(c);

        Author a1 = new Author();
        a1.setName("nekoj1");
        a1.setSurname("nekoj");
        a1.setCountry(c);
        authorRepository.save(a1);

        Author a2 = new Author();
        a2.setName("nekoj2");
        a2.setSurname("nekoj");
        a2.setCountry(c);
        authorRepository.save(a2);

        Book b = new Book();
        b.setAuthor(a1);
        b.setCategory(Category.NOVEL);
        b.setAvailableCopies(3);
        b.setName("Hamlet");
        bookRepository.save(b);
    }

}
