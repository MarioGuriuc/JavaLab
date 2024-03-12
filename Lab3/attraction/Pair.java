package attraction;

// attraction.Pair class implemented using generics which holds a pair of two elements
public class Pair<K, V> {
	private K first;
	private V second;

	public Pair() {
		first = null;
		second = null;
	}

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public Pair(Pair<K, V> obj) {
		this.first = obj.first;
		this.second = obj.second;
	}

	public K getFirst() {
		return this.first;
	}

	public void setFirst(K first) {
		this.first = first;
	}

	public V getSecond() {
		return this.second;
	}

	public void setSecond(V second) {
		this.second = second;
	}
}
