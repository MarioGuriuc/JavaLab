package person;

/**
 * A class to represent a driver.
 *
 * @see AbstractPerson
 */
public class Driver extends AbstractPerson
{
	public Driver(String name, String destination, int age)
	{
		super(name, destination, age);
	}

	@Override
	public String toString()
	{
		return "Driver {" +
				"name='" + name + '\'' +
				", destination='" + destination + '\'' +
				", age=" + age +
				'}';
	}
}
