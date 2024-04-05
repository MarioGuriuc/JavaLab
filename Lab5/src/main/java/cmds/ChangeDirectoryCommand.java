package cmds;

import exceps.IllegalNumberOfArgumentsException;
import exceps.NotLoggedInException;
import exceps.TypeOfCommand;
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
	public void execute() throws IOException
	{
		if (!shell.getLoggedIn())
		{
			throw new NotLoggedInException();
		}
		if (shell.getCommands().length != 2)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.CD);
		}
		String directory = shell.getCommands()[1];
		String path = shell.getRepo().getPath().toString();
		String currentDirectory = shell.getCurrentDirectory();
		if (!directory.equals(".."))
		{
			path = path + "/" + directory;
			currentDirectory = (currentDirectory + directory + "/");
		}
		else
		{
			path = path.substring(0, path.lastIndexOf("/"));
			currentDirectory = ("/" + currentDirectory.substring(0, currentDirectory.lastIndexOf("/")));
		}
		shell.setCurrentDirectory(currentDirectory);
		shell.setRepo(new Repository(path, shell.getRepo().getPerson()));
		System.out.println("Changed directory to: " + shell.getRepo().getPath());
	}
}
