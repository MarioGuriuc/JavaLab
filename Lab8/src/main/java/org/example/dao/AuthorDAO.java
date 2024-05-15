package org.example.dao;

import org.example.book.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends GenericDAO<Author>
{
    public AuthorDAO()
    {
        super();
    }

    @Override
    public void add(Author author)
    {
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, author.getName());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error adding author", e);
        }
    }

    @Override
    public Author findById(int id)
    {
        String sql = "SELECT * FROM authors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return new Author(resultSet.getString("name"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error finding author", e);
        }

        return null;
    }

    @Override
    public int findIdByName(String name)
    {
        String sql = "SELECT id FROM authors WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return resultSet.getInt("id");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error finding author id by name", e);
        }

        return -1;
    }

    @Override
    public List<Author> getAll()
    {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";

        try (Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                authors.add(new Author(resultSet.getString("name")));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error getting all authors", e);
        }

        return authors;
    }

    @Override
    public void update(Author author, int id)
    {
        String sql = "UPDATE authors SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, author.getName());
            statement.setInt(2, id);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error updating author", e);
        }
    }

    @Override
    public void delete(int id)
    {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error deleting author", e);
        }
    }
}
