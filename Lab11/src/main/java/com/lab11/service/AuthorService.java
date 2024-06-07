package com.lab11.service;

import com.lab11.entity.Author;
import com.lab11.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors()
    {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author)
    {
        return authorRepository.save(author);
    }
}
