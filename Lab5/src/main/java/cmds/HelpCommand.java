package cmds;

public class HelpCommand implements Command
{
	@Override
	public void execute()
	{
		System.out.println("Available commands:");
		System.out.println("logout");
		System.out.println("login");
		System.out.println("create <name> <id>");
		System.out.println("ls");
		System.out.println("cd <directory> (use .. to go back a directory)");
		System.out.println("view <file> (no extension)");
		System.out.println("report");
		System.out.println("export");
	}
}
