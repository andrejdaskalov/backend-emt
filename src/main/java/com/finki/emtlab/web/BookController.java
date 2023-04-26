package com.finki.emtlab.web;

import com.finki.emtlab.exception.AuthorNotFoundException;
import com.finki.emtlab.exception.BookNotFoundException;
import com.finki.emtlab.model.Author;
import com.finki.emtlab.model.Book;
import com.finki.emtlab.model.Category;
import com.finki.emtlab.model.dto.BookDto;
import com.finki.emtlab.service.AuthorService;
import com.finki.emtlab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public List<Book> index(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) throws AuthorNotFoundException{
        return ResponseEntity.ok(bookService.save(bookDto));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) throws BookNotFoundException, AuthorNotFoundException{
        return ResponseEntity.ok(bookService.edit(id,bookDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws BookNotFoundException {
        return bookService.delete(id) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

    }

    @GetMapping("/borrow/{id}")
    public ResponseEntity<Book> borrow(@PathVariable Long id) throws BookNotFoundException {
        return bookService.borrowBook(id).map(
                ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/return/{id}")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.returnBook(id).map(
                ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return List.of(Category.values());
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorService.getAll();
    }


}
