import person.Driver;
import person.Passenger;
import person.Person;
import problem.Problem;
import problem.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		List<Person> group = getPeople();

		List<Driver> driverList = group.stream()
				.filter(person -> person instanceof Driver)
				.map(person -> (Driver) person)
				.collect(Collectors.toCollection(ArrayList::new));

		Set<Passenger> passengerList = group.stream()
				.filter(person -> person instanceof Passenger)
				.map(person -> (Passenger) person)
				.collect(Collectors.toCollection(TreeSet::new));

		System.out.println("Drivers sorted by age:");

		driverList.stream()
				.sorted(Comparator.comparingInt(Person::getAge))
				.forEach(System.out::println);

		System.out.println();
		System.out.println("Passengers sorted by name:");

		passengerList.stream()
				.sorted(Comparator.comparing(Person::getName))
				.forEach(System.out::println);

		System.out.println();

		//Problem problem = RandomProblemGenerator.generateProblem();
		Problem problem = new Problem(driverList, new ArrayList<>(passengerList));

		Map<String, List<Person>> map = problem.getDestinationToPersonMap();
		System.out.println();
		System.out.println("Destination to person map:");
		for (var i : map.entrySet())
		{
			System.out.println(i.getKey() + " -> " + i.getValue());
		}
		System.out.println();

		Solution solution = new Solution(problem);
		solution.getSolution();
	}

	private static List<Person> getPeople()
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

		return (List.of(driver1, driver2, driver3, driver4, driver5, passenger1, passenger2,
				passenger3, passenger4, passenger5));
	}
}
