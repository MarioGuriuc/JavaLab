package Lab2.depot;

import Lab2.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Depot class to represent the places where vehicles can leave for delivering packages, and then coming back.
 * It has a bidirectional relationship with the Vehicle class.
 * @see Vehicle
 */

public class Depot {
	private String name;
	private ArrayList<Vehicle> vehicles;

	public Depot(String name) {
		vehicles = new ArrayList<>();
		this.name = name;
	}

	/**
	 * It adds a vehicle to the ArrayList of vehicles, and sets that vehicle depot to the current object.
	 * @param vehicle the vehicle to be added
	 */
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.setDepot(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Depot{" +
				"name='" + name + '\'' +
				", vehicles=" + vehicles +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Depot depot = (Depot) o;
		return Objects.equals(name, depot.name);
	}
}
