package com.finki.emtlab.service.impl;

import com.finki.emtlab.exception.AuthorNotFoundException;
import com.finki.emtlab.exception.BookNotFoundException;
import com.finki.emtlab.model.Book;
import com.finki.emtlab.model.dto.BookDto;
import com.finki.emtlab.repository.AuthorRepository;
import com.finki.emtlab.repository.BookRepository;
import com.finki.emtlab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book delete(Long id) throws BookNotFoundException {
        Book b = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(b);
        return b;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book save(BookDto bookDto) throws AuthorNotFoundException {
        Book book = new Book();
        return setFromDto(bookDto, book);
    }

    @Override
    public Book edit(Long id, BookDto bookDto) throws BookNotFoundException, AuthorNotFoundException {
        Book b = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return setFromDto(bookDto, b);
    }

    private Book setFromDto(BookDto bookDto, Book b) throws AuthorNotFoundException {
        b.setName(bookDto.getName());
        b.setCategory(bookDto.getCategory());
        b.setAvailableCopies(bookDto.getAvailableCopies());
        b.setAuthor(authorRepository.findById(bookDto.getAuthor()).orElseThrow(AuthorNotFoundException::new));

        return save(b);
    }

    @Override
    public Optional<Book> borrowBook(Long bookId) throws BookNotFoundException {
       Book b = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
       if (b.getAvailableCopies() > 0) {
           b.setAvailableCopies(b.getAvailableCopies() - 1);
           bookRepository.save(b);
           return Optional.of(b);
       } else {
           return Optional.empty();
       }
    }
    @Override
    public Optional<Book> returnBook(Long bookId) throws BookNotFoundException {
        Book b = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        b.setAvailableCopies(b.getAvailableCopies() + 1);
        bookRepository.save(b);
        return Optional.of(b);
    }



}
