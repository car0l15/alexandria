package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.service.AuthorService;
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
@RequestMapping("/library/authors")
public class AuthorController {

  public AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping()
  public List<Author> getAllAuthor() {
    return authorService.findAllAuthors();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
    Optional<Author> author = authorService.findAuthorsById(id);
    return ResponseEntity.status(200).body(author.get());
  }

  @PostMapping
  public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
    Author create = authorService.createAuthor(author);
    return ResponseEntity.status(201).body(create);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author request)
  {
    Author update = authorService.updateAuthor(id, request);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
    Author delete = authorService.deleteAuthorById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }

}
