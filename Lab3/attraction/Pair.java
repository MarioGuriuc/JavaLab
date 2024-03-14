package attraction;

// Pair class implemented using generics which holds a pair of two elements
public class Pair<K, V> {
	private K openingHour;
	private V closingHour;

	public Pair() {
		openingHour = null;
		closingHour = null;
	}

	public Pair(K openingHour, V closingHour) {
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}

	public Pair(Pair<K, V> obj) {
		this.openingHour = obj.openingHour;
		this.closingHour = obj.closingHour;
	}

	public K getOpeningHour() {
		return this.openingHour;
	}

	public void setOpeningHour(K openingHour) {
		this.openingHour = openingHour;
	}

	public V getClosingHour() {
		return this.closingHour;
	}

	public void setClosingHour(V closingHour) {
		this.closingHour = closingHour;
	}
}
