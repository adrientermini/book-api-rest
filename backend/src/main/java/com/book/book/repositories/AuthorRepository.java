package com.book.book.repositories;

import com.book.book.entities.AuthorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuthorRepository extends AbstractRepository<AuthorEntity, Long> {

    public AuthorRepository() {
        super(AuthorEntity.class);
    }
}
