package cmds;

import cmds.Command;
import exceps.FileDoesNotExistException;
import filesdiradmin.Document;
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
	public void execute()
	{
		try
		{
			shell.getRepo().listDocuments();
			System.out.print("Enter a file (name + extension): ");
			String pathStr = shell.getRepo().getPath().toString();
			String file = shell.getScanner().nextLine();
			pathStr = pathStr + "/" + file;
			Path filePath = Paths.get(pathStr);
			if (!Files.exists(filePath))
			{
				throw new FileDoesNotExistException();
			}
			Desktop.getDesktop().open(filePath.toFile());
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}