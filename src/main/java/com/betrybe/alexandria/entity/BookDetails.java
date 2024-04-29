package com.betrybe.alexandria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookDetails")
public class BookDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String summary;
  private Integer pagesCount;
  private String year;
  private String isbn;

  @OneToOne(optional = false)
  @JoinColumn(name = "book_id")
  private Book book;

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public BookDetails() {
  }

  public BookDetails(String summary, Integer pagesCount, String year, String isbn) {
    this.summary = summary;
    this.pagesCount = pagesCount;
    this.year = year;
    this.isbn = isbn;
  }

  public Long getId() {
    return id;
  }

//  public void setId(Long id) {
//    this.id = id;
//  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Integer getPagesCount() {
    return pagesCount;
  }

  public void setPagesCount(Integer pagesCount) {
    this.pagesCount = pagesCount;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
}
