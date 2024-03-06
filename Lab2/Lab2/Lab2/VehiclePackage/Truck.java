package Lab2.VehiclePackage;

import Lab2.DepotPackage.Depot;

public class Truck extends Vehicle {

	private final int capacityFuel;

	public Truck(String name, Depot depot, int capacityFuel) {
		super(name, depot);
		this.capacityFuel = capacityFuel;
	}
}
