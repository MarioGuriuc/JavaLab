package exceps;

import java.io.IOException;

public class IllegalNumberOfArgumentsException extends IOException
{
	public IllegalNumberOfArgumentsException(TypeOfCommand type)
	{
		super(getErrorMessage(type));
	}

	private static String getErrorMessage(TypeOfCommand type)
	{
		StringBuilder string = new StringBuilder("Illegal number of arguments for command " + type + "\n");
		switch (type)
		{
			case LOGOUT -> string.append("Usage: logout");
			case LOGIN -> string.append("Usage: login <name> <id>");
			case CREATE -> string.append("Usage: create <name> <id>");
			case LS -> string.append("Usage: ls");
			case CD -> string.append("Usage: cd <directory> (use .. to go back a directory)");
			case VIEW -> string.append("Usage: view <file> (no extension)");
			case REPORT -> string.append("Usage: report");
			case EXPORT -> string.append("Usage: export");
		}
		return string.toString();
	}
}
