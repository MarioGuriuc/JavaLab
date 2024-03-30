package exceps;

import java.io.IOException;

public class RepositoryDoesNotExist extends IOException
{
	public RepositoryDoesNotExist()
	{
		super("Repository does not exist");
	}
}
