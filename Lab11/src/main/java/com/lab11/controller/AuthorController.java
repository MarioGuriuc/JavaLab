package com.lab11.controller;

import com.lab11.entity.Author;
import com.lab11.schemas.AuthorData;
import com.lab11.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author Controller", description = "Operations related to managing authors")
public class AuthorController
{

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieve a list of all authors")
    public List<Author> getAuthors()
    {
        return authorService.getAuthors();
    }

    @PostMapping
    @Operation(summary = "Add a new author", description = "Add a new author to the collection")
    public Author addAuthor(@RequestBody AuthorData author)
    {
        Author newAuthor = new Author(author.getName(), author.getBooks());
        return authorService.addAuthor(newAuthor);
    }
}
