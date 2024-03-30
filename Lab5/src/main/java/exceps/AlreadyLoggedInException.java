package exceps;

import java.io.IOException;

public class AlreadyLoggedInException extends IOException
{
	public AlreadyLoggedInException()
	{
		super("You are already logged in");
	}
}
