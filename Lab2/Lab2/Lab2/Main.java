package Lab2;

import Lab2.ClientPackage.Client;
import Lab2.ClientPackage.ClientType;
import Lab2.DepotPackage.Depot;
import Lab2.ProblemPackage.Problem;
import Lab2.SolutionPackage.Solution;
import Lab2.VehiclePackage.Drone;
import Lab2.VehiclePackage.Truck;
import Lab2.VehiclePackage.Vehicle;

public class Main {
	public static void main(String[] args) {
		Client client = new Client("Mario", ClientType.Premium, 10, 18);
		Depot depot = new Depot("Depot1");
		Vehicle truck = new Truck("Scania", depot, 200);
		Vehicle drone = new Drone("Drone", depot, 300);
		Problem problem = new Problem();
		problem.addDepot(depot);
		problem.addVehicle(truck);
		problem.addVehicle(drone);
		Solution solution = new Solution(problem);
		solution.getSolution();
	}
}
