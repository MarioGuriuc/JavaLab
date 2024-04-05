package filesdiradmin;

import exceps.EmptyDirectoryException;
import exceps.RepositoryDoesNotExist;
import person.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Repository
{
	private Person person;
	private Path path;

	public Repository(String path, Person person) throws IOException
	{
		this.person = person;
		this.path = Paths.get(path);
		if (!Files.exists(this.path))
		{
			throw new RepositoryDoesNotExist();
		}
	}

	public Repository()
	{
		this.path = Paths.get("src/main/MASTER");
	}

	public void listDocuments() throws IOException
	{
		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		if (stream == null)
		{
			throw new EmptyDirectoryException();
		}
		for (Path personDirectory : stream)
		{
			System.out.println(personDirectory.getFileName());
		}
		stream.close();
	}

	public Path getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = Paths.get(path);
	}

	public List<File> getFiles()
	{
		return List.of(Objects.requireNonNull(path.toFile().listFiles()));
	}

	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}
}
