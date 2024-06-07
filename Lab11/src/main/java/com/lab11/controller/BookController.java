package com.lab11.controller;

import com.lab11.entity.Book;
import com.lab11.schemas.BookData;
import com.lab11.schemas.UpdateBookData;
import com.lab11.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Operations related to managing books")
public class BookController
{

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Add a new book to the collection")
    public Book addBook(@RequestBody BookData book)
    {
        Book newBook = new Book(book.getTitle(), book.getAuthorName());
        return bookService.addBook(newBook);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update the details of an existing book")
    public Book updateBook(@PathVariable int id, @RequestBody UpdateBookData newData)
    {
        return bookService.updateBook(id, newData.getTitle());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book from the collection")
    public ResponseEntity<String> deleteBook(@PathVariable int id)
    {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
