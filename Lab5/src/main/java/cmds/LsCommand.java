package cmds;

import exceps.IllegalNumberOfArgumentsException;
import exceps.NotLoggedInException;
import exceps.TypeOfCommand;
import filesdiradmin.Shell;

import java.io.IOException;

public class LsCommand implements Command
{
	private final Shell shell;

	public LsCommand(Shell shell)
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
		if (shell.getCommands().length != 1)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.LS);
		}
		shell.getRepo().listDocuments();
	}
}
