package problem;

import com.github.javafaker.Faker;
import person.Driver;
import person.Passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to generate a random problem for matching drivers and passengers.
 * @see Problem
 */

public class RandomProblemGenerator
{
	public static Problem generateProblem()
	{
		Faker faker = new Faker();
		List<Driver> drivers = new ArrayList<>();
		List<Passenger> passengers = new ArrayList<>();
		int driverCount = faker.number().numberBetween(6, 12);
		int passengerCount = faker.number().numberBetween(6, 12);
		for (int i = 0; i < driverCount; i++)
		{
			drivers.add(new Driver(faker.name().firstName(), faker.address().city(), faker.number().numberBetween(18, 65)));
		}
		for (int i = 0; i < passengerCount; i++)
		{
			passengers.add(new Passenger(faker.name().firstName(), faker.address().city(), faker.number().numberBetween(18, 65)));
		}
		return new Problem(drivers, passengers);
	}
}
