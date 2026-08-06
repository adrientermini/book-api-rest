package com.book.book.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T, ID> {

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public T save(T entity) {
        try {
            Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
            if (id == null) {
                entityManager.persist(entity);
                return entity;
            } else {
                return entityManager.merge(entity);
            }
        } catch (Exception e) {
            // Fallback strategy if getIdentifier fails or if we want to stick to the previous simple logic
            // But since we use sequences and generated IDs, this is generally safe.
            // A more robust way in JPA is to check if the ID is null via reflection or property access if we can't use getPersistenceUnitUtil
            return entityManager.merge(entity);
        }
    }

    public void deleteById(ID id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
