package exceps;

import java.io.IOException;

public class IdIsNotUniqueException extends IOException
{
	public IdIsNotUniqueException()
	{
		super("ID is already in use.");
	}
}
