package exceps;

import java.io.IOException;

public class DirectoryNotCreatedException extends IOException
{
	public DirectoryNotCreatedException()
	{
		super("Directory could not be created");
	}
}
