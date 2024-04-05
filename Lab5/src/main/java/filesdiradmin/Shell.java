package filesdiradmin;

import cmds.*;
import exceps.InvalidCommandException;
import person.IdStorage;

import java.io.IOException;
import java.util.Scanner;

public class Shell
{
	private Repository repo;
	private Boolean loggedIn;
	private String[] commands;
	private String currentDirectory;
	private IdStorage idStorage;

	public Shell()
	{
		repo = new Repository();
		loggedIn = false;
		currentDirectory = "MASTER/";
	}

	public void run()
	{
		initialization();
		while (true)
		{
			try
			{
				System.out.print(currentDirectory + " > ");
				Scanner scanner = new Scanner(System.in);
				String command = scanner.nextLine();
				commands = command.split(" ");
				command = commands[0];
				switch (command)
				{
					case "exit" -> System.exit(0);
					case "help" -> new HelpCommand().execute();
					case "logout" -> new LogoutCommand(this).execute();
					case "login" -> new LoginCommand(this).execute();
					case "create" -> new CreateCommand(this).execute();
					case "ls" -> new LsCommand(this).execute();
					case "cd" -> new ChangeDirectoryCommand(this).execute();
					case "view" -> new ViewCommand(this).execute();
					case "report" -> new ReportCommand(this).execute();
					case "export" -> new ExportCommand(this).execute();
					default -> throw new InvalidCommandException();
				}
			}
			catch (IOException e)
			{
				System.out.println("Fatal: " + e.getMessage());
			}
		}
	}

	private void initialization()
	{
		try
		{
			idStorage = new IdStorage();
		}
		catch (IOException e)
		{
			System.out.println("Fatal: " + e.getMessage());
		}
		new HelpCommand().execute();
		System.out.println();
	}

	public Repository getRepo()
	{
		return repo;
	}

	public void setRepo(Repository repo)
	{
		this.repo = repo;
	}

	public Boolean getLoggedIn()
	{
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}

	public String[] getCommands()
	{
		return commands;
	}

	public String getCurrentDirectory()
	{
		return currentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory)
	{
		this.currentDirectory = currentDirectory;
	}

	public IdStorage getIdStorage()
	{
		return idStorage;
	}
}