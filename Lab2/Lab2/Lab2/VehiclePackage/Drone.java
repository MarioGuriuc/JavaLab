package Lab2.VehiclePackage;

import Lab2.DepotPackage.Depot;

public class Drone extends Vehicle {

	private final int maxFlightDuration;

	public Drone(String name, Depot depot, int maxFlightDuration) {
		super(name, depot);
		this.maxFlightDuration = maxFlightDuration;
	}
}
