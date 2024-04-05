package exceps;

import java.io.IOException;

public class NotLoggedInException extends IOException
{
	public NotLoggedInException()
	{
		super("You are not logged in");
	}
}
