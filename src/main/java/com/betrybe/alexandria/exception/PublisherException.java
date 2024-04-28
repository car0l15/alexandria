package com.betrybe.alexandria.exception;

public class PublisherException extends RuntimeException {
  public PublisherException() {
    super("Editora não encontrada");
  }
}
