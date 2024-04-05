package cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import exceps.IllegalNumberOfArgumentsException;
import exceps.NotLoggedInException;
import exceps.TypeOfCommand;
import filesdiradmin.Repository;
import filesdiradmin.Shell;
import person.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportCommand implements Command
{
	private final Shell shell;

	public ExportCommand(Shell shell)
	{
		this.shell = shell;
	}

	@Override
	public void execute() throws IOException
	{
		if (!shell.getLoggedIn())
		{
			throw new NotLoggedInException();
		}
		if (shell.getCommands().length != 1)
		{
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.EXPORT);
		}
		Repository repository = shell.getRepo();
		Person user = repository.getPerson();

		String filePath = "src/main/MASTER/" + user.name() + user.ID() + "/json_export.json";

		System.out.println("Export completed successfully.");
		System.out.println("Exported to: " + filePath);
	}

	private void export(String filePath) throws IOException
	{
		ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		FileWriter fileWriter = new FileWriter(filePath);
		objectMapper.writeValue(fileWriter, shell.getRepo());
	}
}