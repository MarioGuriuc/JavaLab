package cmds;

import exceps.IdIsNotUniqueException;
import exceps.IllegalNumberOfArgumentsException;
import exceps.TypeOfCommand;
import filesdiradmin.Shell;
import person.Person;

import java.io.IOException;

public class CreateCommand implements Command
{
	private final Shell shell;

	public CreateCommand(Shell shell)
	{
		this.shell = shell;
	}

	@Override
	public void execute() throws IOException
	{
		if (shell.getCommands().length != 3)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.CREATE);
		}
		String name = shell.getCommands()[1];
		String ID = shell.getCommands()[2];
		if (shell.getIdStorage().containsId(Integer.parseInt(ID)))
		{
			throw new IdIsNotUniqueException();
		}
		Person person = new Person(name, Integer.parseInt(ID));
		String path = "src/main/MASTER/" + person.name() + person.ID();
		shell.getIdStorage().addId(person.ID());
		Command.createDirectory(path);
		System.out.println("Repository created successfully");
		System.out.println("To access your repository, login with your name and ID");
	}
}
