package com.book.book.repositories;

import com.book.book.entities.BookEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BookRepository extends AbstractRepository<BookEntity, Long> {

    public BookRepository() {
        super(BookEntity.class);
    }
}
