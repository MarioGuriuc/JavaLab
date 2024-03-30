package cmds;

import exceps.NotLoggedInException;
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
	public void execute()
	{
		try
		{
			if (!shell.getLoggedIn())
			{
				throw new NotLoggedInException();
			}
			shell.getRepo().listDocuments();
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
