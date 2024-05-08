package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.model.entity.Author;
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

/**
 * The type Author controller.
 */
@RestController
@RequestMapping("/library/authors")
public class AuthorController {

  /**
   * The Author service.
   */
  public AuthorService authorService;

  /**
   * Instantiates a new Author controller.
   *
   * @param authorService the author service
   */
  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  /**
   * Gets all author.
   *
   * @return the all author
   */
  @GetMapping()
  public List<Author> getAllAuthor() {
    return authorService.findAllAuthors();
  }

  /**
   * Gets author by id.
   *
   * @param id the id
   * @return the author by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
    Optional<Author> author = authorService.findAuthorsById(id);
    return ResponseEntity.status(200).body(author.get());
  }

  /**
   * Create author response entity.
   *
   * @param author the author
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<List<Author>> createAuthor(@RequestBody List<Author> author) {
   List<Author> create = author.stream().map(authorService::createAuthor).toList();
    return ResponseEntity.status(201).body(create);
  }

  /**
   * Update author response entity.
   *
   * @param id      the id
   * @param request the request
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author request)
  {
    Author update = authorService.updateAuthor(id, request);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
  }

  /**
   * Delete author response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
    Author delete = authorService.deleteAuthorById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }

}
