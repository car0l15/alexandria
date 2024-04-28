package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.PublisherException;
import com.betrybe.alexandria.repository.PublisherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
  public PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public List<Publisher> findAllPublishers() {
    return publisherRepository.findAll();
  }

  public Optional<Publisher> findPubliserById(Long id) {
    Optional<Publisher> publisher = publisherRepository.findById(id);

    if(publisher.isEmpty()) {
      throw new PublisherException();
    }

    return publisher;
  }

  public Publisher createPublisher(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  public Publisher updatePublisher(Long id, Publisher publisher) {

    Optional<Publisher> findPublisher = findPubliserById(id);

    findPublisher.get().setName(publisher.getName());
    findPublisher.get().setAddress(publisher.getAddress());

    return publisherRepository.save(findPublisher.get());
  }

  public Publisher deletePublisher(Long id) {
    Optional<Publisher> publisher = findPubliserById(id);

    publisherRepository.deleteById(id);

    return publisher.get();
  }

}
