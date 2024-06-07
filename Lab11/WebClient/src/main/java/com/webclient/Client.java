package com.webclient;

import com.webclient.model.Author;
import com.webclient.model.Book;
import com.webclient.service.BookClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Client implements CommandLineRunner
{

    @Autowired
    private BookClientService bookClientService;

    public static void main(String[] args)
    {
        SpringApplication.run(Client.class, args);
    }

    @Override
    public void run(String[] args)
    {
        Random random = new Random();

        // Generate random author
        Author author = new Author();
        author.setName("Author " + random.nextInt(1000));
        bookClientService.addAuthor(author);
        System.out.println("Created Author: " + author);

        List<Author> authors = bookClientService.getAuthors();
        authors.forEach(System.out::println);

        // Generate random book
        Book book = new Book();
        book.setTitle("Book " + random.nextInt(1000));
        book.setAuthor(author);
        Book createdBook = bookClientService.addBook(book);
        System.out.println("Created Book: " + createdBook);

        List<Book> books = bookClientService.getBooks();
        books.forEach(System.out::println);

        // Add more random books
        for (int i = 0; i < 5; i++) {
            Book randomBook = new Book();
            randomBook.setTitle("Random Book " + random.nextInt(1000));
            randomBook.setAuthor(authors.get(random.nextInt(authors.size())));
            bookClientService.addBook(randomBook);
        }

        books = bookClientService.getBooks();
        books.forEach(System.out::println);

        bookClientService.deleteBook(createdBook.getId());

        books = bookClientService.getBooks();
        books.forEach(System.out::println);
    }
}
