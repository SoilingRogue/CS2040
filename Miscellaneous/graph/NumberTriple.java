package graph;

/* 
 * ADT structure for edgeList, storing of both nodes and weight of node (can be 0 for unweighted graphs)
 * Implements Comparable for arranging of edges in PQ<NumberTriple>
 */
public class NumberTriple implements Comparable<NumberTriple> {
	private final int node1, node2;
	private final long weight;

	public NumberTriple(int node1, int node2, long weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}

	public int getFirstNode() {
		return this.node1;
	}

	public int getSecondNode() {
		return this.node2;
	}

	public long getWeight() {
		return this.weight;
	}

	// Comparison based on weight, then first node, then second node
	@Override
	public int compareTo(NumberTriple o) {
		final long weightDiff = this.getWeight() - o.getWeight();
		if (weightDiff == 0) {
			final int node1Diff = this.getFirstNode() - o.getFirstNode();
			if (node1Diff == 0) {
				return this.getSecondNode() - o.getSecondNode();
			}
			return node1Diff;
		}
		return weightDiff < 0 ? -1 : 1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstNode());
		sb.append(" to ");
		sb.append(getSecondNode());
		sb.append(" : ");
		sb.append(getWeight());
		return sb.toString();
	}
}
