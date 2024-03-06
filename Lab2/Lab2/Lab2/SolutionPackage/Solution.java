package Lab2.SolutionPackage;

import Lab2.ClientPackage.Client;
import Lab2.DepotPackage.Depot;
import Lab2.ProblemPackage.Problem;
import Lab2.VehiclePackage.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	private final Problem problem;
	private final Map<Pair, Float> times;

	public Solution(Problem problem) {
		this.problem = problem;
		times = new HashMap<>();
		for (Depot depot : problem.getDepots()) {
			for (Client client : problem.getClients()) {
				times.put(new Pair(depot.getName(), client.getName()), (float) (Math.random() * 0.75 + 0.25));
			}
		}
		for (Client client1 : problem.getClients()) {
			for (Client client2 : problem.getClients()) {
				if (!client1.equals(client2)) {
					times.put(new Pair(client1.getName(), client2.getName()), (float) (Math.random() * 0.75 + 0.25));
				}
			}
		}
	}

	public void getSolution() {
		for (Vehicle vehicle : problem.getVehicles()) {
			System.out.println(vehicle.getName() + " from " + vehicle.getDepot().getName());
			for (Client client : problem.getClients()) {
				System.out.println("  " + client.getName() + " " + times.get(new Pair(vehicle.getDepot().getName(),
						client.getName())));
			}
		}
	}
}
