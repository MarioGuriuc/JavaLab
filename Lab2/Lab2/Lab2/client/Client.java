package Lab2.ClientPackage;

import Lab2.VehiclePackage.Vehicle;

import java.util.Objects;

public class Client {
	private String name;
	private ClientType clientType;
	private final float intervalStart;
	private final float intervalEnd;

	public Client(String name, ClientType clientType, float intervalStart, float intervalEnd) {
		this.name = name;
		this.clientType = clientType;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public ClientType getType() {
		return clientType;
	}

	public void setType(ClientType clientType) {
		if (clientType != null) {
			this.clientType = clientType;
		}
	}

	@Override
	public String toString() {
		return "Client{" +
				"name='" + name + '\'' +
				", clientType=" + clientType +
				", intervalStart=" + intervalStart +
				", intervalEnd=" + intervalEnd +
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return intervalStart == client.intervalStart && intervalEnd == client.intervalEnd && Objects.equals(name, client.name) && clientType == client.clientType;
	}
}
