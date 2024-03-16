import person.Driver;
import person.Passenger;
import person.Person;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		List<Person> group = getPeople();

		List<Driver> driverList = group.stream()
				.filter(person -> person instanceof Driver)
				.map(person -> (Driver) person)
				.toList();

		Set<Passenger> passengerList = group.stream()
				.filter(person -> person instanceof Passenger)
				.map(person -> (Passenger) person)
				.collect(Collectors.toSet());

		driverList.stream()
				.sorted(Comparator.comparingInt(Person::getAge))
				.forEach(System.out::println);
		passengerList.stream()
				.sorted(Comparator.comparing(Person::getName))
				.forEach(System.out::println);
	}

	private static LinkedList<Person> getPeople()
	{
		Person driver1 = new Driver("John", "New York", 18);
		Person driver2 = new Driver("Mike", "Los Angeles", 30);
		Person driver3 = new Driver("Sara", "Chicago", 24);
		Person driver4 = new Driver("Tom", "San Francisco", 21);
		Person driver5 = new Driver("Alice", "Seattle", 62);
		Person passenger1 = new Passenger("Bob", "New York", 20);
		Person passenger2 = new Passenger("Eve", "Los Angeles", 25);
		Person passenger3 = new Passenger("Mallory", "Chicago", 30);
		Person passenger4 = new Passenger("Trent", "San Francisco", 35);
		Person passenger5 = new Passenger("Carol", "Seattle", 40);

		return new LinkedList<>(List.of(driver1, driver2, driver3, driver4, driver5, passenger1, passenger2,
				passenger3, passenger4, passenger5));
	}
}
