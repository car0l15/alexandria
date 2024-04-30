package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.BookDetailsException;
import com.betrybe.alexandria.exception.BookException;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Book service.
 */
@Service
public class BookService {

  /**
   * The Book repository.
   */
  public BookRepository bookRepository;
  /**
   * The Book detail repository.
   */
  public BookDetailRepository bookDetailRepository;

  /**
   * The Publisher service.
   */
  public PublisherService publisherService;

  /**
   * Instantiates a new Book service.
   *
   * @param bookRepository       the book repository
   * @param bookDetailRepository the book detail repository
   * @param publisherService     the publisher service
   */
  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository,
      PublisherService publisherService) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherService = publisherService;
  }

  /**
   * Find all books list.
   *
   * @return the list
   */
  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  /**
   * Find book by id optional.
   *
   * @param id the id
   * @return the optional
   */
  public Optional<Book> findBookById(Long id) {
    Optional<Book> book = bookRepository.findById(id);

    if (book.isEmpty()) {
      throw new BookException();
    }

    return book;
  }

  /**
   * Create book book.
   *
   * @param book the book
   * @return the book
   */
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  /**
   * Update book book.
   *
   * @param id   the id
   * @param book the book
   * @return the book
   */
  public Book updateBook(Long id, Book book) {

    Optional<Book> findBook = findBookById(id);

    findBook.get().setGenre(book.getGenre());
    findBook.get().setTitle(book.getTitle());

    return bookRepository.save(findBook.get());
  }

  /**
   * Delete book book.
   *
   * @param id the id
   * @return the book
   */
  public Book deleteBook(Long id) {
    Optional<Book> book = findBookById(id);

    bookRepository.deleteById(id);
    return book.get();
  }

  /**
   * Create book detail book detail.
   *
   * @param bookId      the book id
   * @param bookDetails the book details
   * @return the book detail
   */
  public BookDetail createBookDetail(Long bookId, BookDetail bookDetails) {
    Optional<Book> book = findBookById(bookId);

    bookDetails.setBook(
        book.get()); // nessa linha aqui nós pegamos o objeto encontrado pelo find Book e o
    // associamos a entidade Book que está presente em BookDetails

    return bookDetailRepository.save(bookDetails);

  }

  /**
   * Gets book detail by id.
   *
   * @param bookId the book id
   * @return the book detail by id
   */
  public BookDetail getBookDetailById(Long bookId) {
    Optional<Book> book = findBookById(bookId);

    BookDetail getDetails = book.get().getDetails();

    if(getDetails == null) {
      throw new BookDetailsException(); // um livro pode existir sem um detalhe, apenas o contrário não é permitido
    }

    return getDetails;
  }

  /**
   * Update book dateil book detail.
   *
   * @param bookId     the book id
   * @param bookDetail the book detail
   * @return the book detail
   */
  public BookDetail updateBookDateil(Long bookId, BookDetail bookDetail) {

    BookDetail bookDetailFromDb = getBookDetailById(bookId);

    bookDetailFromDb.setIsbn(bookDetail.getIsbn());
    bookDetailFromDb.setYear(bookDetail.getYear());
    bookDetailFromDb.setSummary(bookDetail.getSummary());
    bookDetailFromDb.setPagesCount(bookDetail.getPagesCount());

    return bookDetailRepository.save(bookDetailFromDb);
  }

  /**
   * Delete book details book detail.
   *
   * @param bookId the book id
   * @return the book detail
   */
  public BookDetail deleteBookDetails(Long bookId) {
    Optional<Book> book = findBookById(bookId);
    BookDetail bookDetail = book.get().getDetails();

    if(bookDetail == null) {
      throw new BookDetailsException(); // verifica se os detalhes existem
    }

    book.get().setDetails(null); // quebra o relacionamento entre elas
    bookDetail.setBook(null); // quebra o relacionamento entre elas

    bookDetailRepository.delete(bookDetail);

    return bookDetail;
  }

  /**
   * Sets book publisher.
   *
   * @param bookId      the book id
   * @param publisherId the publisher id
   * @return the book publisher
   */
  public Book setBookPublisher(Long bookId, Long publisherId) {
   Optional<Book> book = findBookById(bookId);
   Optional<Publisher> publisher = publisherService.findPubliserById(publisherId);

   book.get().setPublisher(publisher.get());

   return bookRepository.save(book.get());
  }

  /**
   * Remove publisher book.
   *
   * @param bookId the book id
   * @return the book
   */
  public Book removePublisher(Long bookId) {
    Optional<Book> book = findBookById(bookId);

    book.get().setPublisher(null);

    return bookRepository.save(book.get());
  }
}

