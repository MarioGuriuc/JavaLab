package cmds;

import exceps.IllegalNumberOfArgumentsException;
import exceps.NotLoggedInException;
import exceps.TypeOfCommand;
import filesdiradmin.Shell;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command
{
	private final Shell shell;

	public ReportCommand(Shell shell)
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
			throw new IllegalNumberOfArgumentsException(TypeOfCommand.REPORT);
		}
		generateReport();
	}

	private void generateReport() throws IOException
	{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
		cfg.setClassForTemplateLoading(ReportCommand.class, "/templates");
		Template template = cfg.getTemplate("report_template.ftl");

		Map<String, Object> data = new HashMap<>();
		data.put("directoryName", shell.getRepo().getPath());
		data.put("files", shell.getRepo().getFiles());

		String outputDirectory = "src/main/MASTER/" + shell.getRepo().getPath().getFileName() + "/directory_report";
		if (!new File(outputDirectory).exists())
		{
			Command.createDirectory(outputDirectory);
		}
		File outputFile = new File(outputDirectory, "directory_report.html");
		try (FileWriter fileWriter = new FileWriter(outputFile);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
		{
			template.process(data, bufferedWriter);
			System.out.println("Report generated successfully");
			System.out.println("Report saved to: " + outputFile.getPath());
		}
		catch (TemplateException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}
