package com.betrybe.alexandria.service;

import com.betrybe.alexandria.model.entity.Author;
import com.betrybe.alexandria.model.entity.Book;
import com.betrybe.alexandria.model.entity.BookDetail;
import com.betrybe.alexandria.model.entity.Publisher;
import com.betrybe.alexandria.exception.BookDetailsException;
import com.betrybe.alexandria.exception.BookException;
import com.betrybe.alexandria.model.repository.BookDetailRepository;
import com.betrybe.alexandria.model.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
   * The Author service.
   */
  public AuthorService authorService;

  /**
   * Instantiates a new Book service.
   *
   * @param bookRepository       the book repository
   * @param bookDetailRepository the book detail repository
   * @param publisherService     the publisher service
   * @param authorService        the author service
   */
  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository,
      PublisherService publisherService, AuthorService authorService) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherService = publisherService;
    this.authorService = authorService;
  }

  /**
   * Find all books list.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the list
   */
  public List<Book> findAllBooks(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Book> page = bookRepository.findAll(pageable);
    return page.toList();
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

   findBook.get().setGenre(Optional.ofNullable(book.getGenre())
       .orElse(findBook.get().getGenre()));
   findBook.get().setTitle(Optional.ofNullable(book.getTitle())
       .orElse(findBook.get().getTitle())
   );

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

    bookDetailFromDb.setIsbn(Optional.ofNullable(bookDetail.getIsbn()).orElse(bookDetailFromDb.getIsbn()));
    bookDetailFromDb.setYear(Optional.ofNullable(bookDetail.getYear()).orElse(bookDetailFromDb.getYear()));
    bookDetailFromDb.setSummary(Optional.ofNullable(bookDetail.getSummary()).orElse(bookDetailFromDb.getSummary()));
    bookDetailFromDb.setPagesCount(Optional.ofNullable(bookDetail.getPagesCount()).orElse(bookDetailFromDb.getPagesCount()));

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

  /**
   * Add book author book.
   *
   * @param bookId   the book id
   * @param authorId the author id
   * @return the book
   */
  public Book addBookAuthor(Long bookId, Long authorId) {
    Optional<Book> book = findBookById(bookId);
    Optional<Author> author = authorService.findAuthorsById(authorId);

    book.get().getAuthors().add(author.get());

    return bookRepository.save(book.get());
  }

  /**
   * Remove book author book.
   *
   * @param bookId   the book id
   * @param authorId the author id
   * @return the book
   */
  public Book removeBookAuthor(Long bookId, Long authorId) {
    Optional<Book> book = findBookById(bookId);
    Optional<Author> author = authorService.findAuthorsById(authorId);

    book.get().getAuthors().removeIf(autor -> autor.getId().equals(authorId));

   return bookRepository.save(book.get());
  }
}

