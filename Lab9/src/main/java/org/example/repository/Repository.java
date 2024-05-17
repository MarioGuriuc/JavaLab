package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entitymanager.EntityManagerFactorySingleton;

import java.util.Optional;

public abstract class Repository<T, ID>
{
    @PersistenceContext
    protected EntityManager entityManager;

    Repository()
    {
        this.entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public abstract <S extends T> S save(S entity);

    public abstract <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    public abstract void delete(T entity);

    public abstract Optional<T> findById(ID id);

    public abstract Iterable<T> findAll();

    public abstract Iterable<T> findAllById(Iterable<ID> ids);

    public abstract long count();

    public abstract void deleteById(ID id);

    public abstract void deleteAll(Iterable<? extends T> entities);

    public abstract void deleteAll();

    public abstract boolean existsById(ID id);
}
