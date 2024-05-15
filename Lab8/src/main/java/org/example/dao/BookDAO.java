package org.example.dao;

import org.example.book.Author;
import org.example.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends GenericDAO<Book>
{
    public BookDAO()
    {
        super();
    }

    @Override
    public void add(Book book)
    {
        AuthorDAO authorDAO = new AuthorDAO();
        String sqlForBook = "INSERT INTO books (title, language, publication_date, num_pages) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statementBook = connection.prepareStatement(sqlForBook))
        {
            statementBook.setString(1, book.getTitle());
            statementBook.setString(2, book.getLanguage());
            statementBook.setDate(3, new java.sql.Date(book.getPublicationDate().getTime()));
            statementBook.setInt(4, book.getNumPages());
            statementBook.executeUpdate();

            // Set the authors
            for (String author : book.getAuthors())
            {
                if (authorDAO.findIdByName(author) == -1)
                {
                    authorDAO.add(new Author(author));
                }
            }

            // Set the book authors relationship
            associateAuthorsWithBook(book, authorDAO);
        }
        catch (SQLException e)
        {
            System.out.println("Cannot add book: " + e.getMessage());
        }
    }

    private void deleteAuthorsByBookId(int bookId)
    {
        String sql = "DELETE FROM book_authors WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Cannot delete authors by book id: " + e.getMessage());
        }
    }

    private void associateAuthorsWithBook(Book book, AuthorDAO authorDAO) throws SQLException
    {
        deleteAuthorsByBookId(findIdByName(book.getTitle()));
        String sqlForBookAuthor = "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)";

        int bookId = findIdByName(book.getTitle());
        for (String author : book.getAuthors())
        {
            int authorId = authorDAO.findIdByName(author);
            try (PreparedStatement statementBookAuthor = connection.prepareStatement(sqlForBookAuthor))
            {
                statementBookAuthor.setInt(1, bookId);
                statementBookAuthor.setInt(2, authorId);
                statementBookAuthor.executeUpdate();
            }
        }
    }

    private List<String> getAuthorsNamesByBookId(int bookId)
    {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT authors.name FROM authors JOIN book_authors ON authors.id = book_authors.author_id WHERE book_authors.book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    authors.add(resultSet.getString("name"));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Cannot get authors by book id: " + e.getMessage());
        }
        return authors;
    }

    @Override
    public List<Book> getAll()
    {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next())
            {
                Book book = new Book(
                        resultSet.getString("title"),
                        resultSet.getString("language"),
                        resultSet.getDate("publication_date"),
                        getAuthorsNamesByBookId(resultSet.getInt("id")),
                        resultSet.getInt("num_pages"));
                books.add(book);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Cannot get books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book findById(int id)
    {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return new Book(
                            resultSet.getString("title"),
                            resultSet.getString("language"),
                            resultSet.getDate("publication_date"),
                            getAuthorsNamesByBookId(id),
                            resultSet.getInt("num_pages")
                    );
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Cannot find book by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int findIdByName(String name)
    {
        String sql = "SELECT id FROM books WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getInt("id");
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Cannot find book id by name: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public void update(Book book, int id)
    {
        // Update the book table and also the book_authors table
        String sqlForBook = "UPDATE books SET title = ?, language = ?, publication_date = ?, num_pages = ? WHERE id = ?";
        try (PreparedStatement statementBook = connection.prepareStatement(sqlForBook))
        {
            statementBook.setString(1, book.getTitle());
            statementBook.setString(2, book.getLanguage());
            statementBook.setDate(3, new java.sql.Date(book.getPublicationDate().getTime()));
            statementBook.setInt(4, book.getNumPages());
            statementBook.setInt(5, id);
            statementBook.executeUpdate();

            // Update the authors
            AuthorDAO authorDAO = new AuthorDAO();
            for (String author : book.getAuthors())
            {
                if (authorDAO.findIdByName(author) == -1)
                {
                    authorDAO.add(new Author(author));
                }
            }

            // Update the book authors relationship
            associateAuthorsWithBook(book, authorDAO);
        }
        catch (SQLException e)
        {
            System.out.println("Cannot update book: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id)
    {
        String sqlForBookAuthors = "DELETE FROM book_authors WHERE book_id = ?";
        String sqlForBook = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statementBookAuthors = connection.prepareStatement(sqlForBookAuthors);
             PreparedStatement statementBook = connection.prepareStatement(sqlForBook))
        {
            statementBookAuthors.setInt(1, id);
            statementBook.setInt(1, id);
            statementBookAuthors.executeUpdate();
            statementBook.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Cannot delete book: " + e.getMessage());
        }
    }
}
