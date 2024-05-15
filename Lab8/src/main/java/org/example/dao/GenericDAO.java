package org.example.dao;

import org.example.db.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public abstract class GenericDAO<T>
{
    protected final Connection connection;

    public GenericDAO()
    {
        try
        {
            connection = DatabaseConnection.getConnection();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Cannot connect to the database", e);
        }
    }

    public abstract void add(T t);

    public abstract T findById(int id);

    public abstract int findIdByName(String name);

    public abstract List<T> getAll();

    public abstract void update(T t, int id);

    public abstract void delete(int id);
}
