package cmds;

import exceps.DirectoryExistsException;
import exceps.DirectoryNotCreatedException;

import java.io.File;
import java.io.IOException;

public interface Command
{
	static void createDirectory(String path) throws DirectoryNotCreatedException, DirectoryExistsException
	{
		File directory = new File(path);
		if (!directory.exists())
		{
			boolean success = directory.mkdirs();
			if (!success)
			{
				throw new DirectoryNotCreatedException();
			}
		}
		else
		{
			throw new DirectoryExistsException();
		}
	}

	void execute() throws IOException;
}