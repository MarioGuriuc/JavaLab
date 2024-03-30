package exceps;

import java.io.IOException;

public class FileDoesNotExistException extends IOException
{
	public FileDoesNotExistException()
	{
		super("File does not exist");
	}
}
