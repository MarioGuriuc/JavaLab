package cmds;

import exceps.NotLoggedInException;
import filesdiradmin.Repository;
import filesdiradmin.Shell;

import java.io.IOException;

public class ChangeDirectoryCommand implements Command
{
	private final Shell shell;

	public ChangeDirectoryCommand(Shell shell)
	{
		this.shell = shell;
	}

	@Override
	public void execute()
	{
		try
		{
			if (!shell.getLoggedIn())
			{
				throw new NotLoggedInException();
			}
			System.out.print("Enter a directory: ");
			System.out.println("Enter '..' to go back a directory");
			String directory = shell.getScanner().nextLine();
			String path = shell.getRepo().getPath().toString();
			if (!directory.equals(".."))
			{
				path = path + "/" + directory;
			}
			else
			{
				path = path.substring(0, path.lastIndexOf("/"));
			}
			shell.setRepo(new Repository(path, shell.getRepo().getPerson()));
			System.out.println("Changed directory to: " + shell.getRepo().getPath());
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
