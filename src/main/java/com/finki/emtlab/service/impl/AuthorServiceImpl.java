package com.finki.emtlab.service.impl;

import com.finki.emtlab.model.Author;
import com.finki.emtlab.repository.AuthorRepository;
import com.finki.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
