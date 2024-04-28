package com.betrybe.alexandria.exception;

public class BookException extends RuntimeException {
  public BookException() {
    super("Livro não encontrado");
  }
}
