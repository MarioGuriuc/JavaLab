package cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceps.NotLoggedInException;
import filesdiradmin.Repository;
import filesdiradmin.Shell;

import java.io.File;
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
	public void execute()
	{
		try
		{
			if (!shell.getLoggedIn())
			{
				throw new NotLoggedInException();
			}
			Repository repository = shell.getRepo();
			List<File> files = repository.getFiles();

			String filename = "repository.json";

			export(files, filename);

			System.out.println("Export completed successfully.");
		}
		catch (IOException e)
		{
			System.err.println("Error exporting repository: " + e.getMessage());
		}
	}

	private void export(List<File> files, String filename) throws IOException
	{
		List<String> filePaths = files.stream()
				.map(File::getAbsolutePath)
				.toList();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(filename), filePaths);
	}
}