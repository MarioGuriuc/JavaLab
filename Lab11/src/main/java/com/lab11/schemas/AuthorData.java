package com.lab11.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AuthorData
{
    private String name;
    private List<BookData> books;
}
