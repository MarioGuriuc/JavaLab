package Lab2.Lab2.vehicle;

import Lab2.Lab2.depot.Depot;

public class Truck extends Vehicle {

	private final int capacityFuel;

	public Truck(String name, Depot depot, int capacityFuel) {
		super(name, depot);
		this.capacityFuel = capacityFuel;
	}
}
