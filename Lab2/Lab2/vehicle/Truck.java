package Lab2.vehicle;

/**
 * Truck class inherited from the Vehicle class.
 * @see Vehicle
 */
public class Truck extends Vehicle {

	private final int capacityFuel;

	public Truck(String name, int capacityFuel) {
		super(name);
		this.capacityFuel = capacityFuel;
	}
}
