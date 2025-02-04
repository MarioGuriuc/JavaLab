package org.example;

import org.example.book.Author;
import org.example.book.Book;
import org.example.dao.AuthorDAO;
import org.example.dao.BookDAO;
import org.example.db.DatabaseConnection;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        new Main().startShell();
    }

    private void startShell()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bookstore!");
        System.out.println("Type 'help' to see the available commands.");
        System.out.println("Type 'exit' to exit the application.");

        while (true)
        {
            System.out.print("> ");
            String command = scanner.nextLine();

            switch (command)
            {
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Exiting the application...");
                    DatabaseConnection.closeConnection();
                    return;
                case "add author":
                    addAuthor();
                    break;
                case "add book":
                    addBook();
                    break;
                case "list authors":
                    listAuthors();
                    break;
                case "list books":
                    listBooks();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to see the available commands.");
            }
        }
    }

    private void listBooks()
    {
        for (Book book : new BookDAO().getAll())
        {
            System.out.println(book);
        }
    }

    private void listAuthors()
    {
        for (Author author : new AuthorDAO().getAll())
        {
            System.out.println(author);
        }
    }

    private void addBook()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the book's title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book's language: ");
        String language = scanner.nextLine();
        System.out.print("Enter the book's publication date (yyyy-mm-dd): ");
        String publicationDate = scanner.nextLine();
        System.out.print("Enter the book's number of pages: ");
        int numPages = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the book's authors (comma-separated): ");
        String authors = scanner.nextLine();

        BookDAO bookDAO = new BookDAO();
        bookDAO.add(new Book(title, language, Date.valueOf(publicationDate), List.of(authors.split(",")), numPages));
    }

    private void addAuthor()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the author's name: ");
        String name = scanner.nextLine();

        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.add(new Author(name));
    }

    private void printHelp()
    {
        System.out.println("Available commands:");
        System.out.println("add author - Add an author");
        System.out.println("add book - Add a book");
        System.out.println("list authors - List all authors");
        System.out.println("list books - List all books");
        System.out.println("exit - Exit the application");
    }
}
