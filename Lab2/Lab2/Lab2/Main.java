package Lab2.Lab2;

import Lab2.Lab2.client.Client;
import Lab2.Lab2.client.ClientType;
import Lab2.Lab2.depot.Depot;
import Lab2.Lab2.problem.Problem;
import Lab2.Lab2.solution.Solution;
import Lab2.Lab2.vehicle.Drone;
import Lab2.Lab2.vehicle.Truck;
import Lab2.Lab2.vehicle.Vehicle;

public class Main {
	public static void main(String[] args) {
		Client client = new Client("Mario", ClientType.Premium, 10, 18);
		System.out.println(client);
		Depot depot = new Depot("Depot1");
		System.out.println(depot);
		Vehicle truck = new Truck("Scania", depot, 200);
		Vehicle drone = new Drone("Drone", depot, 300);
		System.out.println(truck);
		System.out.println(drone);
		Problem problem = new Problem();
		problem.addDepot(depot);
		problem.addVehicle(truck);
		problem.addVehicle(drone);
		Solution solution = new Solution(problem);
		solution.getSolution();
	}
}
