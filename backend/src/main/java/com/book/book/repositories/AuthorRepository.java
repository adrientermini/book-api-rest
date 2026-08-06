package com.book.book.repositories;

import com.book.book.entities.AuthorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class AuthorRepository extends AbstractRepository<AuthorEntity, Long> {

    public AuthorRepository() {
        super(AuthorEntity.class);
    }
}
