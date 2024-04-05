package exceps;

import java.io.IOException;

public class InvalidCommandException extends IOException
{
	public InvalidCommandException()
	{
		super("Invalid command");
	}
}
