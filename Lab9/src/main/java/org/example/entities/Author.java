package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "Author.findAllById", query = "SELECT a FROM Author a WHERE a.id IN :ids"),
        @NamedQuery(name = "Author.findById", query = "SELECT a FROM Author a WHERE a.id = :id"),
        @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = :name"),
        @NamedQuery(name = "Author.count", query = "SELECT COUNT(a) FROM Author a"),
        @NamedQuery(name = "Author.deleteAll", query = "DELETE FROM Author a"),
        @NamedQuery(name = "Author.deleteById", query = "DELETE FROM Author a WHERE a.id = :id"),
        @NamedQuery(name = "Author.existsById", query = "SELECT COUNT(a) FROM Author a WHERE a.id = :id")
})
public class Author
{
    private String name;

    @Id
    private Long id;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    private Set<Book> books = new LinkedHashSet<>();

}
