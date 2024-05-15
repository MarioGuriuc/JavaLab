package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "STUDENT";
    private static final String PASSWORD = "STUDENT";
    private static final DataSource dataSource;

    static
    {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public static void closeConnection()
    {
        try
        {
            dataSource.unwrap(HikariDataSource.class).close();
        }
        catch (SQLException e)
        {
            System.out.println("Cannot close connection: " + e.getMessage());
        }
    }
}
