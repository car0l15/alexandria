package com.betrybe.alexandria.advice;

import com.betrybe.alexandria.exception.AuthorException;
import com.betrybe.alexandria.exception.BookDetailsException;
import com.betrybe.alexandria.exception.BookException;
import com.betrybe.alexandria.exception.PublisherException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(AuthorException.class)
  public ResponseEntity<String> handleAuthorNotFound(AuthorException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado, tente um id válido! ");
  }

  @ExceptionHandler(BookException.class)
  public ResponseEntity<String> handleBooksNotFound(BookException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado, tente um id válido");
  }

  @ExceptionHandler(PublisherException.class)
  public ResponseEntity<String> handlePublisherNotFound(PublisherException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não encontrada, tente um id válido!");
  }

  @ExceptionHandler(BookDetailsException.class)
    public ResponseEntity<String> handleBookDetailsNotFound(BookDetailsException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este livro não possui detalhes");
  }
}

