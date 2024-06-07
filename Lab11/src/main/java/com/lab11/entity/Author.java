package com.lab11.entity;

import com.fasterxml.jackson.annotation.*;
import com.lab11.schemas.BookData;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> books;

    public Author(String authorName)
    {
        this.name = authorName;
    }

    @JsonProperty("bookTitles")
    public List<String> getBookTitles()
    {
        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    public Author(String authorName, List<BookData> books)
    {
        this.name = authorName;
        this.books = new ArrayList<>();
        for (BookData bookData : books)
        {
            this.books.add(new Book(bookData.getTitle(), bookData.getAuthorName()));
        }
    }
}
