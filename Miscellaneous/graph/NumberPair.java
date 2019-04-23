package graph;

// ADT structure for AdjacencyList, storing of second node and weight of node (can be 0 for unweighted graphs)
public class NumberPair {
	private final int node;
	private final long weight;

	public NumberPair(int node, long weight) {
		this.node = node;
		this.weight = weight;
	}

	public long getWeight() {
		return this.weight;
	}

	public int getNode() {
		return this.node;
	}
}
