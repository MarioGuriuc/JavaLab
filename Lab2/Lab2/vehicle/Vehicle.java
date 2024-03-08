package Lab2.vehicle;

import Lab2.client.Client;
import Lab2.depot.Depot;

import java.util.ArrayList;
import java.util.Objects;
/**
 * Abstract class to represent vehicles. It has a bidirectional relationship to the Depot class.
 * Contains an ArrayList of clients, that is used in the Problem class.
 * @see Depot
 * @see Lab2.problem.Problem
 * @see ArrayList
 */
public abstract class Vehicle {
	private String name;
	private Depot depot;
	private ArrayList<Client> clients;

	public Vehicle(String name) {
		clients = new ArrayList<>();
		this.name = name;
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

	public void addClient(Client client) {
		clients.add(client);
	}

	public ArrayList<Client> getClients() {
		return this.clients;
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
