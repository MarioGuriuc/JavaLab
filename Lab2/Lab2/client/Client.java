package Lab2.client;

import java.util.Objects;

/**
 * The client class is used to represent the clients where packages must be delivered.
 */

public class Client {
	private final float timeStart;
	private final float timeEnd;
	private String name;
	private ClientType clientType;

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

	/**
	 * Sets the type of the client.
	 *
	 * @param clientType an enum object
	 * @see ClientType
	 */

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
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return timeStart == client.timeStart && timeEnd == client.timeEnd && Objects.equals(name, client.name) && clientType == client.clientType;
	}

	public float getTimeStart() {
		return timeStart;
	}

	public float getTimeEnd() {
		return timeEnd;
	}
}
