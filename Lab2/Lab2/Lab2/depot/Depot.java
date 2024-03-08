package Lab2.Lab2.depot;

import Lab2.Lab2.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

public class Depot {
	private String name;
	private ArrayList<Vehicle> vehicles;

	public Depot(String name) {
		vehicles = new ArrayList<>();
		this.name = name;
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
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Depot depot = (Depot) o;
		return Objects.equals(name, depot.name);
	}
}
