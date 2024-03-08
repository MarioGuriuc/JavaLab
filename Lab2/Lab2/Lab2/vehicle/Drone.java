package Lab2.Lab2.vehicle;

import Lab2.Lab2.depot.Depot;

public class Drone extends Vehicle {

	private final int maxFlightDuration;

	public Drone(String name, Depot depot, int maxFlightDuration) {
		super(name, depot);
		this.maxFlightDuration = maxFlightDuration;
	}
}
