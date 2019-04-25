package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/* 
 * Finding shortest path from in a directed weighted/unweighted graph - ie. path of smallest weight or
 * least num nodes. Cannot be used in graphs with negative weight CYCLES since repeating the cycle will
 * cause weight to be more and more negative. Negative weight EDGES in acyclic graphs are still acceptable.
 * Unweighted graphs: BFS to find shortest path - runs in O(V + E)
 * Graphs without negative weight edges & cycle: Dijkstra's algo - runs in O((V + E) log V)
 * Graphs without negative weight cycle: Modified Dijkstra's - runs in O((V + E) log V) 
 * General graphs: Bellman Ford - runs in O(V * E)
 * Tree: DFS/BF - runs in O(V) 
 * */
public class SSSP {
	private ArrayList<LinkedList<NumberPair>> adjList;
	private ArrayList<NumberTriple> edgeList;
	private long[] distance;
	private int[] parent;
	private boolean[] visited;

	private void run() {
		Scanner sc = new Scanner(System.in);
		final int numVertices = sc.nextInt();
		final int numEdges = sc.nextInt();
		adjList = new ArrayList<>();
		edgeList = new ArrayList<>();
		distance = new long[numVertices];
		parent = new int[numVertices];
		visited = new boolean[numVertices];
		final int start = sc.nextInt();
		final int end = sc.nextInt();

		// Set initial distance of all vertices to be Long.MAX_VALUE & initialize
		// adjList
		for (int i = 0; i < numVertices; ++i) {
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
			adjList.add(new LinkedList<NumberPair>());
		}

		// Storing of input into adjList and edgeList
		for (int i = 0; i < numEdges; ++i) {
			final int first = sc.nextInt();
			final int second = sc.nextInt();
			final long weight = sc.nextLong();
			adjList.get(first).addLast(new NumberPair(second, weight));
			edgeList.add(new NumberTriple(first, second, weight));
		}

		bellmanFord(start);
		dijkstra(start);
		bfs(start);

		System.out.println(distance[end]);
	}

	// Most versatile but slowest algo to find SSSP
	private void bellmanFord(int source) {
		// for vertices 1 to v-1
		// for edges in edgelist
		// relax
		// check for cycle, break if cycle exists + set boolean flag to true
	}

	// PQ ADT - Directed weighted graph with NO negative weight cycle
	private void dijkstra(int source) {
		PriorityQueue<DijkNode> pq = new PriorityQueue<>();
		distance[source] = 0;
		pq.add(new DijkNode(source, 0));
		while (!pq.isEmpty()) {
			DijkNode curr = pq.poll();
			int vertex = curr.getVertex();
			long weight = curr.getWeight();

			if (distance[vertex] != weight) {
				continue;
			}

			for (NumberPair np : adjList.get(vertex)) {
				int nextVertex = np.getNode();
				long nextWeight = np.getWeight() + weight;

				if (nextWeight < distance[nextVertex]) {
					distance[nextVertex] = nextWeight;
					pq.add(new DijkNode(nextVertex, nextWeight));
				}
			}
		}
	}

	// Queue ADT - Only for unweighted graphs. Finds least number of edges traversed
	private void bfs(int source) {
		// Assuming node 0 is the root node - any node will do
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(source);
		parent[source] = -1;
		distance[source] = 0;
		while (!queue.isEmpty()) {
			int vertex = queue.pollFirst();
			LinkedList<NumberPair> lst = adjList.get(vertex);
			for (NumberPair np : lst) {
				final int node = np.getNode();
				if (!visited[node]) {
					// weight of each edge = 1 in unweighted graphs
					relax(vertex, node, 1);
					queue.add(node);
				}
			}
			visited[vertex] = true;
		}
	}

	// Stack ADT - for weighted trees.
	private void dfs() {

	}

	// for updating shortest distance between two nodes
	private boolean relax(int u, int v, long weight) {
		// If distance can be shortened
		if (distance[v] > distance[u] + weight) {
			distance[v] = distance[u] + weight; // relax the edge that can be shortened
			parent[v] = u; // update parent of v
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		SSSP sssp = new SSSP();
		sssp.run();
	}
}
