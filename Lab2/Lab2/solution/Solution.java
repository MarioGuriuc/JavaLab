package Lab2.solution;


import Lab2.problem.Problem;

/**
 * Solution class with the purpose of receiving a Vehicle Routing Problem and solving it.
 * @see Problem
 */

public class Solution {
	private final Problem problem;

	/**
	 * Constructor for the Solution class. It sets the attributes accordingly and sorts the ArrayList of clients.
	 * @param problem an object of type Problem
	 * @see Problem
	 */
	public Solution(Problem problem) {
		this.problem = problem;
		var clients = problem.getClients();
		int n = clients.size();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				var client1 = clients.get(i);
				var client2 = clients.get(j);
				float diffClient1 = Math.abs(client1.getTimeStart() - client1.getTimeEnd());
				float diffClient2 = Math.abs(client2.getTimeStart() - client2.getTimeEnd());
				if (diffClient1 > diffClient2) {
					clients.set(i, client2);
					clients.set(j, client1);
				}
			}
		}
	}

	/**
	 * Method to solve the problem and print the solution to the output.
	 * It allocates at most 3 clients to a vehicle.
	 */
	public void getSolution() {
		int vehiclesIndex = problem.getVehicles().size() - 1;
		var vehiclesArray = problem.getVehicles();
		var clientsArray = problem.getClients();
		int clientsIndex = 0;
		while (vehiclesIndex >= 0 && clientsIndex != clientsArray.size()) {
			vehiclesArray.get(vehiclesIndex).addClient(clientsArray.get(clientsIndex++));
			if (vehiclesArray.get(vehiclesIndex).getClients().size() == 3) {
				vehiclesIndex--;
			}
		}
		StringBuilder result = new StringBuilder("Solution for the problem is:\n");
		for (var vehicle : vehiclesArray) {
			result.append(vehicle.toString()).append(vehicle.getClients().toString()).append('\n');
		}
		System.out.println(result);
	}
}
