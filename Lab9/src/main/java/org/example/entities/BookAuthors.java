package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BOOK_AUTHORS")
public class BookAuthors
{
    @Id
    private Long id;

    @MapsId("bookId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @MapsId("authorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;
}
