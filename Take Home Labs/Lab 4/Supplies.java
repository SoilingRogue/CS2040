
/*
 * Name       : Yuan Xinran, Stanley
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class Supplies {
	// private class to store edges and weights
	private class Edge implements Comparable<Edge> {
		private final int first, second, cost;

		public Edge(int first, int second, int cost) {
			this.first = first;
			this.second = second;
			this.cost = cost;
		}

		public int getFirst() {
			return this.first;
		}

		public int getSecond() {
			return this.second;
		}

		public int getCost() {
			return this.cost;
		}

		@Override
		public int compareTo(Edge other) {
			return this.getCost() - other.getCost();
		}
	}

	// For Dijkstra's
	private class DijkNode implements Comparable<DijkNode> {
		private final int vertex, cost;

		public DijkNode(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		public int getVertex() {
			return this.vertex;
		}

		public int getCost() {
			return this.cost;
		}

		@Override
		public int compareTo(DijkNode other) {
			return this.getCost() - other.getCost();
		}
	}

	private class NumberPair {
		private final int node, cost;

		public NumberPair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		public int getNode() {
			return this.node;
		}

		public int getCost() {
			return this.cost;
		}
	}

	// Variable declaration
	private int numBusStops, numEdges, start, toObtain, finalDistance;
	// private ArrayList<Edge> edgeList;
	private ArrayList<LinkedList<NumberPair>> adjList;
	private int[] distance, parent;

	private void run() {
		// Variable instantiation
		Scanner sc = new Scanner(System.in);
		numBusStops = sc.nextInt();
		numEdges = sc.nextInt();
		start = sc.nextInt();
		toObtain = sc.nextInt();
		distance = new int[numBusStops];
		parent = new int[numBusStops];
		adjList = new ArrayList<>();
		// edgeList = new ArrayList<Edge>();
		for (int i = 0; i < numBusStops; ++i) {
			distance[i] = 1000000000;
			parent[i] = -1;
			adjList.add(new LinkedList<NumberPair>());
		}

		for (int i = 0; i < numEdges; ++i) {
			int first = sc.nextInt();
			int second = sc.nextInt();
			int cost = sc.nextInt();
			adjList.get(first).addLast(new NumberPair(second, cost));
		}

		// Run first pass from start
		// bellmanFord(start);
		dijkstra(start);

		// get intermediate distance
		finalDistance = distance[toObtain];

		// check if distance to mid is valid
		if (finalDistance >= 1000000000) {
			System.out.println(-1);
		} else {
			// reset distance
			for (int i = 0; i < distance.length; ++i) {
				distance[i] = 1000000000;
			}

			// run second pass from mid;
			// bellmanFord(toObtain)
			dijkstra(toObtain);
			finalDistance += distance[start];

			if (finalDistance >= 1000000000) {
				System.out.println(-1);
			} else {
				System.out.println(finalDistance);
			}
		}
	}

	private void dijkstra(int source) {
		PriorityQueue<DijkNode> pq = new PriorityQueue<>();
		distance[source] = 0;
		pq.add(new DijkNode(source, 0));
		while (!pq.isEmpty()) {
			DijkNode curr = pq.poll();
			int vertex = curr.getVertex();
			int cost = curr.getCost();

			if (distance[vertex] != cost) {
				continue;
			}

			for (NumberPair np : adjList.get(vertex)) {
				int nextVertex = np.getNode();
				int nextCost = np.getCost() + cost;

				if (nextCost < distance[nextVertex]) {
					distance[nextVertex] = nextCost;
					pq.add(new DijkNode(nextVertex, nextCost));
				}
			}
		}
	}

	private void bellmanFord(int source) {
		// for vertices 1 to v-1
		// for edges in edgelist
		// relax
		// check for cycle, break if cycle exists + set boolean flag to true
		/*
		 * boolean tof = relax(curr.getFirst(), curr.getSecond(), curr.getCost());
		 * 
		 * if (tof) { for (NumberPair np : adjList.get(curr.getSecond())) { pq.add(new
		 * Edge(curr.getSecond(), np.getNode(), np.getCost())); } }
		 */
	}

	private boolean relax(int u, int v, int weight) {
		if (distance[v] > distance[u] + weight) {
			distance[v] = distance[u] + weight;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Supplies newSupplies = new Supplies();
		newSupplies.run();
	}
}
