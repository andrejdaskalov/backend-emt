package com.finki.emtlab.service;

import com.finki.emtlab.exception.AuthorNotFoundException;
import com.finki.emtlab.exception.BookNotFoundException;
import com.finki.emtlab.model.Book;
import com.finki.emtlab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public Book findById(Long id) throws BookNotFoundException;
    public Book delete(Long id) throws BookNotFoundException;
    public Book save(Book book);


    Book save(BookDto bookDto) throws AuthorNotFoundException;

    Book edit(Long id, BookDto bookDto) throws BookNotFoundException, AuthorNotFoundException;

    Optional<Book> borrowBook(Long bookId) throws BookNotFoundException;

    Optional<Book> returnBook(Long bookId) throws BookNotFoundException;
}
