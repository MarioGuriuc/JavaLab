package exceps;

import java.io.IOException;

public class EmptyDirectoryException extends IOException
{
	public EmptyDirectoryException()
	{
		super("Directory is empty");
	}
}
