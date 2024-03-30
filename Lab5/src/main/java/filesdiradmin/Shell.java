package filesdiradmin;

import cmds.*;
import exceps.InvalidCommandException;

import java.io.IOException;
import java.util.Scanner;

public class Shell
{
	Scanner scanner;
	private Repository repo;
	private Boolean loggedIn;

	public Shell()
	{
		repo = new Repository();
		scanner = new Scanner(System.in);
		loggedIn = false;
	}

	public void run()
	{
		while (true)
		{
			System.out.print("Enter a command: ");
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();

			try
			{
				switch (command.toLowerCase())
				{
					case "exit" -> System.exit(0);
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
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public Scanner getScanner()
	{
		return scanner;
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
}
