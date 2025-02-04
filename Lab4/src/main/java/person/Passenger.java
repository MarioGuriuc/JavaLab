package person;

/**
 * A class to represent a passenger.
 *
 * @see AbstractPerson
 */
public class Passenger extends AbstractPerson
{
	public Passenger(String name, String destination, int age)
	{
		super(name, destination, age);
	}

	@Override
	public String toString()
	{
		return "Passenger {" +
				"name='" + name + '\'' +
				", destination='" + destination + '\'' +
				", age=" + age +
				'}';
	}
}
