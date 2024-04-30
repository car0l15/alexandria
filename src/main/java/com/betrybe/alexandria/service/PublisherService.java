package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.PublisherException;
import com.betrybe.alexandria.repository.PublisherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Publisher service.
 */
@Service
public class PublisherService {

  /**
   * The Publisher repository.
   */
  public PublisherRepository publisherRepository;

  /**
   * Instantiates a new Publisher service.
   *
   * @param publisherRepository the publisher repository
   */
  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  /**
   * Find all publishers list.
   *
   * @return the list
   */
  public List<Publisher> findAllPublishers() {
    return publisherRepository.findAll();
  }

  /**
   * Find publiser by id optional.
   *
   * @param id the id
   * @return the optional
   */
  public Optional<Publisher> findPubliserById(Long id) {
    Optional<Publisher> publisher = publisherRepository.findById(id);

    if(publisher.isEmpty()) {
      throw new PublisherException();
    }

    return publisher;
  }

  /**
   * Create publisher publisher.
   *
   * @param publisher the publisher
   * @return the publisher
   */
  public Publisher createPublisher(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  /**
   * Update publisher publisher.
   *
   * @param id        the id
   * @param publisher the publisher
   * @return the publisher
   */
  public Publisher updatePublisher(Long id, Publisher publisher) {

    Optional<Publisher> findPublisher = findPubliserById(id);

    findPublisher.get().setName(publisher.getName());
    findPublisher.get().setAddress(publisher.getAddress());

    return publisherRepository.save(findPublisher.get());
  }

  /**
   * Delete publisher publisher.
   *
   * @param id the id
   * @return the publisher
   */
  public Publisher deletePublisher(Long id) {
    Optional<Publisher> publisher = findPubliserById(id);

    publisherRepository.deleteById(id);

    return publisher.get();
  }

}
