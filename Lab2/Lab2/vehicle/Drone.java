package Lab2.vehicle;

/**
 * Drone class inherited from the Vehicle class.
 * @see Vehicle
 */
public class Drone extends Vehicle {

	private final int maxFlightDuration;

	public Drone(String name, int maxFlightDuration) {
		super(name);
		this.maxFlightDuration = maxFlightDuration;
	}
}
