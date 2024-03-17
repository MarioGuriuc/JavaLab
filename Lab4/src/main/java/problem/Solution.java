package problem;

import person.Driver;
import person.Passenger;
import person.Person;

import java.util.*;

public class Solution
{
	Problem problem;

	public Solution(Problem problem)
	{
		this.problem = problem;
	}

	public void getSolution()
	{
		List<Driver> drivers = new ArrayList<>(problem.drivers);
		List<Passenger> passengers = new ArrayList<>(problem.passengers);

		drivers.sort(Comparator.comparing(Driver::getDestination));
		passengers.sort(Comparator.comparing(Passenger::getDestination));

		List<Person> matchedPairs = new ArrayList<>();

		for (Driver driver : drivers)
		{
			for (Passenger passenger : passengers)
			{
				if (driver.getDestination().equals(passenger.getDestination()))
				{
					matchedPairs.add(driver);
					matchedPairs.add(passenger);
					passengers.remove(passenger);
					break;
				}
			}
		}

		System.out.println("Matched pairs:");
		for (Person person : matchedPairs)
		{
			System.out.println(person);
		}
	}
}
