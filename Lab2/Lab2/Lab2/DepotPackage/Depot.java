package Lab2.DepotPackage;

import Lab2.VehiclePackage.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

public class Depot {
	private String name;

	public Depot(String name) {
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
