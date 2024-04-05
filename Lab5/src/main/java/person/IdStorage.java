package person;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class IdStorage
{
	private Set<Integer> ids;

	public IdStorage() throws IOException
	{
		ids = new HashSet<>();
		String fileName = "src/main/java/person/person_ids.json";
		File file = new File(fileName);
		if (!file.exists())
		{
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("[]");
			fileWriter.close();
		}
		else
		{
			ObjectMapper mapper = new ObjectMapper();
			ids = mapper.readValue(file, new TypeReference<>() {});
		}
	}

	public void addId(int id) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String fileName = "src/main/java/person/person_ids.json";
		File file = new File(fileName);

		ids.add(id);
		mapper.writeValue(file, ids);
	}

	public boolean containsId(int id)
	{
		return ids.contains(id);
	}
}
