package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.service.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/books")
public class BookController {
  public BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.findAllBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookService.findBookById(id);
    return ResponseEntity.status(200).body(book.get());
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    Book create = bookService.createBook(book);
    return ResponseEntity.status(201).body(create);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

    Book updateBook = bookService.updateBook(id, book);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateBook);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable Long id) {

    Book delete = bookService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }

}
