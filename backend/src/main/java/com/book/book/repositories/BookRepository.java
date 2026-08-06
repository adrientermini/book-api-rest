package com.book.book.repositories;

import com.book.book.entities.BookEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BookRepository extends AbstractRepository<BookEntity, Long> {

    public BookRepository() {
        super(BookEntity.class);
    }
}
