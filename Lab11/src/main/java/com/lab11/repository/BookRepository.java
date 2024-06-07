package com.lab11.repository;

import com.lab11.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
    default Book updateBookByTitle(int id, String newTitle)
    {
        Book book = findById(id).orElse(null);
        if (book != null)
        {
            book.setTitle(newTitle);
            return save(book);
        }
        return null;
    }
}
