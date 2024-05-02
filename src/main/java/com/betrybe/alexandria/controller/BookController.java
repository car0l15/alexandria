package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.service.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Book controller.
 */
@RestController
@RequestMapping("/library/books")
public class BookController {

  /**
   * The Book service.
   */
  public BookService bookService;

  /**
   * Instantiates a new Book controller.
   *
   * @param bookService the book service
   */
  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Gets all books.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all books
   */
  @GetMapping
  public List<Book> getAllBooks(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "10") int pageSize
  ) {
    List<Book> paiganatedBooks = bookService.findAllBooks(pageNumber, pageSize);
    return paiganatedBooks.stream().toList();
  }

  /**
   * Gets book by id.
   *
   * @param id the id
   * @return the book by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookService.findBookById(id);
    return ResponseEntity.status(200).body(book.get());
  }

  /**
   * Create books response entity.
   *
   * @param books the books
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> books) {
    List<Book> createdBooks = books.stream()
        .map(bookService::createBook).toList();
    return ResponseEntity.status(201).body(createdBooks);
  }

  /**
   * Update book response entity.
   *
   * @param id   the id
   * @param book the book
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

    Book updateBook = bookService.updateBook(id, book);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateBook);

  }

  /**
   * Delete book response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable Long id) {

    Book delete = bookService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }

  /**
   * Create book detail response entity.
   *
   * @param bookId     the book id
   * @param bookDetail the book detail
   * @return the response entity
   */
  @PostMapping("/{bookId}/detail")
  public ResponseEntity<BookDetail> createBookDetail(@PathVariable Long bookId,
      @RequestBody BookDetail bookDetail) {
    BookDetail create = bookService.createBookDetail(bookId, bookDetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(create);
  }

  /**
   * Gets book detail by id.
   *
   * @param bookId the book id
   * @return the book detail by id
   */
  @GetMapping("/{bookId}/detail")
  public ResponseEntity<BookDetail> getBookDetailById(@PathVariable Long bookId)
  {
    BookDetail detail = bookService.getBookDetailById(bookId);
    return ResponseEntity.status(200).body(detail);
  }

  /**
   * Update datail response entity.
   *
   * @param bookId     the book id
   * @param bookDetail the book detail
   * @return the response entity
   */
  @PutMapping("/{bookId}/detail")
  public ResponseEntity<BookDetail> updateDatail(@PathVariable Long bookId,
      @RequestBody BookDetail bookDetail) {
    BookDetail update = bookService.updateBookDateil(bookId, bookDetail);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
  }

  /**
   * Delete detail response entity.
   *
   * @param bookId the book id
   * @return the response entity
   */
  @DeleteMapping("/{bookId}/detail")
  public ResponseEntity<BookDetail> deleteDetail(@PathVariable Long bookId) {
    BookDetail delete = bookService.deleteBookDetails(bookId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }

  /**
   * Sets book publisher.
   *
   * @param bookId      the book id
   * @param publisherId the publisher id
   * @return the book publisher
   */
  @PutMapping("/{bookId}/publisher/{publisherId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Book setBookPublisher(@PathVariable Long bookId,
      @PathVariable Long publisherId) {
    return bookService.setBookPublisher(bookId, publisherId);
  }

  /**
   * Remove book publisher book.
   *
   * @param bookId the book id
   * @return the book
   */
  @DeleteMapping("/{bookId}/publisher")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Book removeBookPublisher(@PathVariable Long bookId) {
    return bookService.removePublisher(bookId);
  }

  /**
   * Add book author book.
   *
   * @param bookId   the book id
   * @param authorId the author id
   * @return the book
   */
  @PutMapping("/{bookId}/authors/{authorId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Book addBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    return bookService.addBookAuthor(bookId, authorId);
  }

  /**
   * Remove book author book.
   *
   * @param bookId   the book id
   * @param authorId the author id
   * @return the book
   */
  @DeleteMapping("/{bookId}/authors/{authorId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Book removeBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    return bookService.removeBookAuthor(bookId, authorId);
  }
}
