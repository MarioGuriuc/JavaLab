package Lab2;

import Lab2.client.Client;
import Lab2.client.ClientType;
import Lab2.depot.Depot;
import Lab2.problem.Problem;
import Lab2.solution.Solution;
import Lab2.vehicle.Drone;
import Lab2.vehicle.Truck;
import Lab2.vehicle.Vehicle;

public class Main {
	public static void main(String[] args) {
		Client client = new Client("Mario", ClientType.PREMIUM, 10, 18);
		Client client1 = new Client("Bolfa", ClientType.REGULAR, 6, 20);
		Client client2 = new Client("Sorin", ClientType.PREMIUM, 15, 19);
		Client client3 = new Client("Pinte", ClientType.PREMIUM, 13, 20);
		Client client4 = new Client("Ioana", ClientType.PREMIUM, 14, 18);
		System.out.println(client);
		Depot depot = new Depot("Depot1");
		System.out.println(depot);
		Vehicle truck = new Truck("Scania", 200);
		Vehicle drone = new Drone("Drone", 300);
		depot.addVehicle(new Truck("Iveco", 250));
		depot.addVehicle(truck);
		depot.addVehicle(drone);
		System.out.println(depot);
		Problem problem = new Problem();
		problem.addDepot(depot);
		problem.addVehicle(truck);
		problem.addVehicle(drone);
		problem.addClient(client);
		problem.addClient(client1);
		problem.addClient(client2);
		problem.addClient(client3);
		problem.addClient(client4);
		problem.addClient(new Client("Ryuk", ClientType.REGULAR, 18, 20));
		Solution solution = new Solution(problem);
		solution.getSolution();
	}
}
