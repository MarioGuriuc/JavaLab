package org.example.repository;

import org.example.entities.Book;

import java.util.List;
import java.util.Optional;

public class BookRepository extends Repository<Book, Long>
{
    @Override
    public <S extends Book> S save(S entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities)
    {
        for (S entity : entities)
        {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public void delete(Book entity)
    {
        entityManager.remove(entity);
    }

    @Override
    public Optional<Book> findById(Long id)
    {
        List<Book> resultList = entityManager.createNamedQuery("Book.findById", Book.class)
                .setParameter("id", id)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public Iterable<Book> findAll()
    {
        return entityManager.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> ids)
    {
        return entityManager.createNamedQuery("Book.findAllById", Book.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public long count()
    {
        return entityManager.createNamedQuery("Book.count", Long.class).getSingleResult();
    }

    @Override
    public void deleteById(Long id)
    {
        entityManager.createNamedQuery("Book.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities)
    {
        for (Book entity : entities)
        {
            entityManager.remove(entity);
        }
    }

    @Override
    public void deleteAll()
    {
        entityManager.createNamedQuery("Book.deleteAll").executeUpdate();
    }

    @Override
    public boolean existsById(Long id)
    {
        Long count = entityManager.createNamedQuery("Book.existsById", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count != null && count > 0;
    }
}
