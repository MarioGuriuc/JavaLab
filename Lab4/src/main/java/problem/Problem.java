package problem;

import person.Driver;
import person.Passenger;
import person.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to represent a problem which scope is to match drivers and passengers,
 * such that the number of matching is maximum.
 * @see Person
 * @see Driver
 * @see Passenger
 */

public class Problem
{
	List<Driver> drivers;
	List<Passenger> passengers;
	List<Person> group;

	public Problem(List<Driver> drivers, List<Passenger> passengers)
	{
		this.drivers = drivers;
		this.passengers = passengers;
		group = new ArrayList<>();
		group.addAll(drivers);
		group.addAll(passengers);
	}

	public List<String> getDestinations()
	{
		return drivers.stream()
				.map(Driver::getDestination)
				.toList();
	}

	public Map<String, List<Person>> getDestinationToPersonMap()
	{
		return group.stream()
				.collect(Collectors.groupingBy(Person::getDestination));
	}
}
