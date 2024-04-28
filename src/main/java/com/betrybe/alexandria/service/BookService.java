package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.exception.BookException;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  public BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> findBookById(Long id) {
    Optional<Book> book = bookRepository.findById(id);

    if(book.isEmpty()) {
      throw new BookException();
    }

    return book;
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book updateBook(Long id,Book book) {

    Optional<Book> findBook = findBookById(id);

    findBook.get().setGenre(book.getGenre());
    findBook.get().setTitle(book.getTitle());

    return bookRepository.save(findBook.get());
  }

  public Book deleteBook(Long id) {
    Optional<Book> book = findBookById(id);

    bookRepository.deleteById(id);
    return book.get();
  }

}
