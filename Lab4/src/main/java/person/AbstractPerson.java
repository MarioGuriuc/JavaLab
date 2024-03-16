package person;

public abstract class AbstractPerson implements Person
{
	protected String name;
	protected String destination;
	protected int age;

	public AbstractPerson(String name, String destination, int age)
	{
		this.name = name;
		this.destination = destination;
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
}
