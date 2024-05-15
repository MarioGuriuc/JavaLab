package org.example.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book
{
    private String title;
    private String language;
    private Date publicationDate;
    private List<String> authors;
    private int numPages;
}
