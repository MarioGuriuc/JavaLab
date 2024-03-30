package cmds;

import exceps.NotLoggedInException;
import filesdiradmin.Repository;
import filesdiradmin.Shell;

public class LogoutCommand implements Command
{
	private final Shell shell;

	public LogoutCommand(Shell shell)
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
			shell.setRepo(null);
			shell.setLoggedIn(false);
			System.out.println("Logged out successfully");
		}
		catch (NotLoggedInException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}


