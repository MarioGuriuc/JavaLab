package com.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.webclient.model.Author;
import com.webclient.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class BookClientService
{

    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080";

    @Autowired
    public BookClientService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<Book> getBooks()
    {
        String url = BASE_URL + "/books";
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, Book[].class)));
    }

    public Book addBook(Book book)
    {
        String url = BASE_URL + "/books";
        try
        {
            return restTemplate.postForObject(url, book, Book.class);
        }
        catch (HttpServerErrorException e)
        {
            System.out.println("Book already exists");
        }
        return null;
    }

    public void deleteBook(int id)
    {
        String url = BASE_URL + "/books/" + id;
        restTemplate.delete(url);
    }

    public List<Author> getAuthors()
    {
        String url = BASE_URL + "/authors";
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, Author[].class)));
    }

    public Author addAuthor(Author author)
    {
        String url = BASE_URL + "/authors";
        try
        {
            return restTemplate.postForObject(url, author, Author.class);
        }
        catch (HttpServerErrorException e)
        {
            System.out.println("Author already exists");
        }
        return null;
    }
}
