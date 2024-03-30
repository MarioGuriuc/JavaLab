package exceps;

import java.io.IOException;

public class DirectoryExistsException extends IOException
{
	public DirectoryExistsException()
	{
		super("Directory already exists");
	}
}
