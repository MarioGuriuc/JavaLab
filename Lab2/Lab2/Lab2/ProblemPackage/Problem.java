package Lab2.ProblemPackage;

import Lab2.ClientPackage.Client;
import Lab2.DepotPackage.Depot;
import Lab2.VehiclePackage.Vehicle;

import java.util.ArrayList;

public class Problem {
	private ArrayList<Depot> depots;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Client> clients;

	public Problem() {
		depots = new ArrayList<>();
		vehicles = new ArrayList<>();
		clients = new ArrayList<>();
	}

	public void addDepot(Depot depot) {
		if (!depots.contains(depot)) {
			depots.add(depot);
		}
	}

	public void addVehicle(Vehicle vehicle) {
		if (!vehicles.contains(vehicle)) {
			vehicles.add(vehicle);
		}
	}

	public void addClient(Client client) {
		if (!clients.contains(client)) {
			clients.add(client);
		}
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
	public ArrayList<Depot> getDepots() {
		return depots;
	}
}
