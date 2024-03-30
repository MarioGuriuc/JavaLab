package cmds;

import filesdiradmin.Shell;
import person.Person;

import java.io.IOException;
import java.util.Scanner;

public class CreateCommand implements Command
{
	private final Shell shell;

	public CreateCommand(Shell shell)
	{
		this.shell = shell;
	}

	@Override
	public void execute()
	{
		try
		{
			System.out.print("Enter a name: ");
			String name = shell.getScanner().nextLine();
			System.out.print("Enter your ID: ");
			String ID = shell.getScanner().nextLine();
			Person person = new Person(name, Integer.parseInt(ID));
			String path = "src/main/MASTER/" + person.name() + person.ID();
			Command.createDirectory(path);
			System.out.println("Repository created successfully");
			System.out.println("To access your repository, login with your name and ID");
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
