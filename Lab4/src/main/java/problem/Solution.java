package problem;

import person.Driver;
import person.Passenger;
import person.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * A class to represent a solution for the problem of matching drivers and passengers,
 * such that the number of matching is maximum.
 * @see Problem
 * @see Driver
 * @see Passenger
 */
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
