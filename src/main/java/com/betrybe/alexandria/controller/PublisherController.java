package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.service.PublisherService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Publisher controller.
 */
@RestController
@RequestMapping("/library/publishers")
public class PublisherController {

  /**
   * The Publisher service.
   */
  public PublisherService publisherService;

  /**
   * Instantiates a new Publisher controller.
   *
   * @param publisherService the publisher service
   */
  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  /**
   * Gets all publishers.
   *
   * @return the all publishers
   */
  @GetMapping
  public List<Publisher> getAllPublishers() { return publisherService.findAllPublishers(); }

  /**
   * Gets publishers by id.
   *
   * @param id the id
   * @return the publishers by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Publisher> getPublishersById(@PathVariable Long id) {
    Optional<Publisher> publisher =publisherService.findPubliserById(id);
    return ResponseEntity.status(200).body(publisher.get());
  }

  /**
   * Create publisher response entity.
   *
   * @param publisher the publisher
   * @return the response entity
   */
  @PostMapping
  public  ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
    Publisher create = publisherService.createPublisher(publisher);

    return ResponseEntity.status(201).body(create);
  }

  /**
   * Update publisher response entity.
   *
   * @param id        the id
   * @param publisher the publisher
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
    Publisher update = publisherService.updatePublisher(id, publisher);
    return ResponseEntity.status(202).body(update);
  }

  /**
   * Delete publisher response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id) {
    Publisher delete = publisherService.deletePublisher(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(delete);
  }
}
