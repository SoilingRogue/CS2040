package graph;

//ADT structure for Dijkstra SSP, storing of source node and weight of node - used in combination with adjList
public class DijkNode implements Comparable<DijkNode> {
	private final int vertex;
	private final long weight;

	public DijkNode(int vertex, long weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	public int getVertex() {
		return this.vertex;
	}

	public long getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(DijkNode other) {
		long result = this.getWeight() - other.getWeight();
		return result < 0 ? -1 : result > 0 ? 1 : 0;
	}
}