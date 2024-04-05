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
	public void execute() throws NotLoggedInException
	{
		if (!shell.getLoggedIn())
		{
			throw new NotLoggedInException();
		}
		shell.setRepo(new Repository());
		shell.setLoggedIn(false);
		shell.setCurrentDirectory("MASTER/");
		System.out.println("Logged out successfully");
	}
}


