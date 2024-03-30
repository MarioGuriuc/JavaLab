package cmds;

import exceps.AlreadyLoggedInException;
import filesdiradmin.Shell;
import person.Person;

import java.io.IOException;

public class LoginCommand implements Command
{
	private final Shell shell;

	public LoginCommand(Shell shell)
	{
		this.shell = shell;
	}

	public void execute()
	{
		try
		{
			if (shell.getLoggedIn())
			{
				throw new AlreadyLoggedInException();
			}
			else
			{
				System.out.print("Enter the name: ");
				String name = shell.getScanner().nextLine();
				System.out.print("Enter the ID: ");
				String ID = shell.getScanner().nextLine();
				int IDint = Integer.parseInt(ID);
				String path = "src/main/MASTER/" + name + ID;
				shell.getRepo().setPath(path);
				shell.getRepo().setPerson(new Person(name, IDint));
				shell.setLoggedIn(true);
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
