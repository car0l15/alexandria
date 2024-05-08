package com.betrybe.alexandria.service;


import com.betrybe.alexandria.model.entity.Author;
import com.betrybe.alexandria.exception.AuthorException;
import com.betrybe.alexandria.model.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Author service.
 */
@Service
public class AuthorService {

  /**
   * The Author repository.
   */
  public AuthorRepository authorRepository;

  /**
   * Instantiates a new Author service.
   *
   * @param authorRepository the author repository
   */
  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  /**
   * Find all authors list.
   *
   * @return the list
   */
  public List<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  /**
   * Find authors by id optional.
   *
   * @param id the id
   * @return the optional
   */
  public Optional<Author> findAuthorsById(Long id) {
    Optional<Author> author = authorRepository.findById(id);

    if(author.isEmpty())
    {
      throw new AuthorException();
    }

    return author;
  }

  /**
   * Create author author.
   *
   * @param author the author
   * @return the author
   */
  public Author createAuthor (Author author) {
    Author newAuthor = authorRepository.save(author);
    return newAuthor;
  }

  /**
   * Update author author.
   *
   * @param id     the id
   * @param author the author
   * @return the author
   */
  public Author updateAuthor(Long id, Author author) {
    Optional<Author> findAuthor = findAuthorsById(id);

    findAuthor.get().setName(Optional.ofNullable(author.getName())
        .orElse(findAuthor.get().getName()));

    findAuthor.get().setNationality(Optional.ofNullable(author.getNationality())
        .orElse(findAuthor.get().getNationality()));

    return authorRepository.save(findAuthor.get());
  }

  /**
   * Delete author by id author.
   *
   * @param id the id
   * @return the author
   */
  public Author deleteAuthorById(Long id) {
    Optional<Author> author = findAuthorsById(id);

    authorRepository.deleteById(id);

    return author.get();
  }

}
