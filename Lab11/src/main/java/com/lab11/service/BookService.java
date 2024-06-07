package com.lab11.service;

import com.lab11.entity.Book;
import com.lab11.repository.AuthorRepository;
import com.lab11.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }

    public Book addBook(Book book)
    {
        if (book.getAuthor().getId() == null)
        {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book);
    }

    public Book updateBook(int id, String newTitle) throws RuntimeException
    {
        return bookRepository.updateBookByTitle(id, newTitle);
    }

    public void deleteBook(int id)
    {
        bookRepository.deleteById(id);
    }
}
