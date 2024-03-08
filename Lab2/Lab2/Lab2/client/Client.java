package Lab2.Lab2.client;

import java.util.Objects;

public class Client {
	private String name;
	private ClientType clientType;
	private final float timeStart;
	private final float timeEnd;

	public Client(String name, ClientType clientType, float timeStart, float timeEnd) {
		this.name = name;
		this.clientType = clientType;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
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
				", intervalStart=" + timeStart +
				", intervalEnd=" + timeEnd +
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return timeStart == client.timeStart && timeEnd == client.timeEnd && Objects.equals(name, client.name) && clientType == client.clientType;
	}
}
