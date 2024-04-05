package problem;

import person.Driver;
import person.Passenger;
import person.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * A class to represent a solution for the problem of matching drivers and passengers,
 * such that the number of matching is maximum.
 *
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
		TreeSet<Driver> sortedDrivers = new TreeSet<>(problem.drivers);
		TreeSet<Passenger> sortedPassengers = new TreeSet<>(problem.passengers);

		List<Person> matchedPairs = new ArrayList<>();

		for (var driverIterator = sortedDrivers.iterator(); driverIterator.hasNext(); )
		{
			Driver driver = driverIterator.next();
			for (var passengerIterator = sortedPassengers.iterator(); passengerIterator.hasNext(); )
			{
				Passenger passenger = passengerIterator.next();
				if (driver.getDestination().equals(passenger.getDestination()))
				{
					matchedPairs.add(driver);
					matchedPairs.add(passenger);
					passengerIterator.remove();
					break;
				}
			}
			if (matchedPairs.contains(driver))
			{
				driverIterator.remove();
			}
		}

		printSolution(matchedPairs, sortedDrivers, sortedPassengers);
	}

	private static void printSolution(List<Person> matchedPairs, TreeSet<Driver> sortedDrivers, TreeSet<Passenger> sortedPassengers)
	{
		if (!matchedPairs.isEmpty())
		{
			System.out.println("Matched pairs:");
		}
		int i = 0;
		for (Person person : matchedPairs)
		{
			System.out.println(person);
			if ((i++ % 2) == 1)
			{
				System.out.println();
			}
		}

		if (sortedDrivers.isEmpty() && sortedPassengers.isEmpty())
		{
			System.out.println("All drivers and passengers have been matched.");
		}
		else
		{
			System.out.println("Not all drivers and passengers have been matched.");
			System.out.println("Remaining drivers:");
			sortedDrivers.forEach(System.out::println);
			System.out.println();
			System.out.println("Remaining passengers:");
			sortedPassengers.forEach(System.out::println);
		}
	}
}
