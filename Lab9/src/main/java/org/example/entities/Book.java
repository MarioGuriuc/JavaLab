package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.findAllById", query = "SELECT b FROM Book b WHERE b.id IN :ids"),
        @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
        @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
        @NamedQuery(name = "Book.findByLanguage", query = "SELECT b FROM Book b WHERE b.language = :language"),
        @NamedQuery(name = "Book.findByPublicationDate", query = "SELECT b FROM Book b WHERE b.publicationDate = :publicationDate"),
        @NamedQuery(name = "Book.findByNumPages", query = "SELECT b FROM Book b WHERE b.numPages = :numPages"),
        @NamedQuery(name = "Book.count", query = "SELECT COUNT(b) FROM Book b"),
        @NamedQuery(name = "Book.deleteAll", query = "DELETE FROM Book b"),
        @NamedQuery(name = "Book.deleteById", query = "DELETE FROM Book b WHERE b.id = :id"),
        @NamedQuery(name = "Book.existsById", query = "SELECT COUNT(b) FROM Book b WHERE b.id = :id")
})
public class Book
{
    private String title;
    private String language;
    private Date publicationDate;
    @ManyToMany
    @JoinTable(name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<Author> authors;

    private int numPages;

    @Id
    private Long id;
}
