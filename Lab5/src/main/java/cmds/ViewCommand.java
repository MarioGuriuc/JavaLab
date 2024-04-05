package cmds;

import exceps.FileDoesNotExistException;
import exceps.IllegalNumberOfArgumentsException;
import exceps.TypeOfCommand;
import filesdiradmin.Shell;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewCommand implements Command
{
	private final Shell shell;

	public ViewCommand(Shell shell)
	{
		this.shell = shell;
	}

	@Override
	public void execute() throws IOException
	{
		if (shell.getCommands().length != 2)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.VIEW);
		}
		String pathStr = shell.getRepo().getPath().toString();
		String file = shell.getCommands()[1];
		pathStr = pathStr + "/" + file;
		Path filePath = Paths.get(pathStr);
		if (!Files.exists(filePath))
		{
			throw new FileDoesNotExistException();
		}
		Desktop.getDesktop().open(filePath.toFile());
	}
}