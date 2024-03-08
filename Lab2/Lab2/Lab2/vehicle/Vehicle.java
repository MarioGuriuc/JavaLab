package Lab2.Lab2.vehicle;

import Lab2.Lab2.client.Client;
import Lab2.Lab2.depot.Depot;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Vehicle {
	private String name;
	private Depot depot;
	private ArrayList<Client> clients;

	public Vehicle(String name, Depot depot) {
		this.name = name;
		this.depot = depot;
		clients = new ArrayList<>();
	}

	public void addClient(Client client) {
		clients.add(client);
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"name='" + name + '\'' +
				", depot=" + depot.getName() +
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vehicle vehicle = (Vehicle) o;
		return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot);
	}
}
