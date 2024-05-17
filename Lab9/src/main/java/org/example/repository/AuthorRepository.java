package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entities.Author;

import java.util.Optional;

public class AuthorRepository extends Repository<Author, Long>
{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public <S extends Author> S save(S entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends Author> Iterable<S> saveAll(Iterable<S> entities)
    {
        for (S entity : entities)
        {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public void delete(Author entity)
    {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id)
    {
        Author author = entityManager.find(Author.class, id);
        if (author != null)
        {
            entityManager.remove(author);
        }
    }

    @Override
    public Optional<Author> findById(Long id)
    {
        Author author = entityManager.find(Author.class, id);
        return author == null ? Optional.empty() : Optional.of(author);
    }

    @Override
    public Iterable<Author> findAll()
    {
        return entityManager.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> ids)
    {
        return entityManager.createNamedQuery("Author.findAllById", Author.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public long count()
    {
        return entityManager.createNamedQuery("Author.count", Long.class).getSingleResult();
    }

    @Override
    public void deleteAll(Iterable<? extends Author> entities)
    {
        for (Author author : entities)
        {
            entityManager.remove(author);
        }
    }

    @Override
    public void deleteAll()
    {
        entityManager.createNamedQuery("Author.deleteAll").executeUpdate();
    }

    @Override
    public boolean existsById(Long id)
    {
        return entityManager.createNamedQuery("Author.existsById", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }
}
