package cmds;

import exceps.AlreadyLoggedInException;
import exceps.IllegalNumberOfArgumentsException;
import exceps.RepositoryDoesNotExist;
import exceps.TypeOfCommand;
import filesdiradmin.Shell;
import person.Person;

import java.io.File;
import java.io.IOException;

public class LoginCommand implements Command
{
	private final Shell shell;

	public LoginCommand(Shell shell)
	{
		this.shell = shell;
	}

	public void execute() throws IOException
	{
		if (shell.getLoggedIn())
		{
			throw new AlreadyLoggedInException();
		}
		if (shell.getCommands().length != 3)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.LOGIN);
		}
		String name = shell.getCommands()[1].toLowerCase();
		String ID = shell.getCommands()[2];
		int IDint = Integer.parseInt(ID);
		String path = "src/main/MASTER/" + name + ID;
		File repoPath = new File(path);
		if (!repoPath.exists())
		{
			throw new RepositoryDoesNotExist();
		}
		shell.getRepo().setPath(path);
		shell.setCurrentDirectory(shell.getCurrentDirectory() + name + ID);
		shell.getRepo().setPerson(new Person(name, IDint));
		shell.setLoggedIn(true);
	}
}
