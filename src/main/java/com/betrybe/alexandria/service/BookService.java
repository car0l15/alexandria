package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.exception.BookDetailsException;
import com.betrybe.alexandria.exception.BookException;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  public BookRepository bookRepository;
  public BookDetailRepository bookDetailRepository;

  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
  }

  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> findBookById(Long id) {
    Optional<Book> book = bookRepository.findById(id);

    if (book.isEmpty()) {
      throw new BookException();
    }

    return book;
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book updateBook(Long id, Book book) {

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

  public BookDetail createBookDetail(Long bookId, BookDetail bookDetails) {
    Optional<Book> book = findBookById(bookId);

    bookDetails.setBook(
        book.get()); // nessa linha aqui nós pegamos o objeto encontrado pelo find Book e o
    // associamos a entidade Book que está presente em BookDetails

    return bookDetailRepository.save(bookDetails);

  }

  public BookDetail getBookDetailById(Long bookId) {
    Optional<Book> book = findBookById(bookId);

    BookDetail getDetails = book.get().getDetails();

    if(getDetails == null) {
      throw new BookDetailsException(); // um livro pode existir sem um detalhe, apenas o contrário não é permitido
    }

    return getDetails;
  }

  public BookDetail updateBookDateil(Long bookId, BookDetail bookDetail) {

    BookDetail bookDetailFromDb = getBookDetailById(bookId);

    bookDetailFromDb.setIsbn(bookDetail.getIsbn());
    bookDetailFromDb.setYear(bookDetail.getYear());
    bookDetailFromDb.setSummary(bookDetail.getSummary());
    bookDetailFromDb.setPagesCount(bookDetail.getPagesCount());

    return bookDetailRepository.save(bookDetailFromDb);
  }

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
}

