package com.lab11.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @JsonProperty("authorName")
    public String getAuthorName()
    {
        return author != null ? author.getName() : null;
    }

    public Book(String title, String authorName)
    {
        this.title = title;
        this.author = new Author(authorName);
    }
}
