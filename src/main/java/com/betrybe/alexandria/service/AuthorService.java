package com.betrybe.alexandria.service;


import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.exception.AuthorException;
import com.betrybe.alexandria.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  public AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  public Optional<Author> findAuthorsById(Long id) {
    Optional<Author> author = authorRepository.findById(id);

    if(author.isEmpty())
    {
      throw new AuthorException();
    }

    return author;
  }

  public Author createAuthor (Author author) {
    Author newAuthor = authorRepository.save(author);
    return newAuthor;
  }

  public Author updateAuthor(Long id, Author author) {
    Optional<Author> findAuthor = findAuthorsById(id);

    findAuthor.get().setName(author.getName());
    findAuthor.get().setNationality(author.getNationality());

//    return bookRepository.save(findBook.get());

    return authorRepository.save(findAuthor.get());
  }

  public Author deleteAuthorById(Long id) {
    Optional<Author> author = findAuthorsById(id);

    authorRepository.deleteById(id);

    return author.get();
  }

}
