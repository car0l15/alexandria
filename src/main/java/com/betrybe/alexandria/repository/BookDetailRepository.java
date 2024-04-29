package com.betrybe.alexandria.repository;

import com.betrybe.alexandria.entity.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetails, Long> {

}
