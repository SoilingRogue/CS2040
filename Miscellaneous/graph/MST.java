package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/* Basic technique to create a MST - minimum spanning tree using Kruskal and Prims algorithm from a graph
 * **Assumes undirected weighted graph** 
 * Uses ArrayList<List<NumberPair>> as an AdjacencyList - NumberPair - other Node, Weight
 * Also uses List<NumberTriple> as an EdgeList - NumberTriple - Node 1, Node 2, Weight
 * */
public class MST {
	private ArrayList<LinkedList<NumberPair>> adjList;
	private ArrayList<NumberTriple> edgeList;
	private boolean[] visited;
	private long weight;

	private void run() {
		// Declaring variables
		Scanner sc = new Scanner(System.in);
		final int numVertices = sc.nextInt();
		final int numEdges = sc.nextInt();
		adjList = new ArrayList<>();
		edgeList = new ArrayList<>();
		visited = new boolean[numVertices];

		// Adjacency List - initializing all LinkedLists in adjList
		for (int i = 0; i < numVertices; ++i) {
			adjList.add(new LinkedList<NumberPair>());
		}

		for (int i = 0; i < numEdges; ++i) {
			int firstNode = sc.nextInt();
			int secondNode = sc.nextInt();
			long weight = sc.nextLong();
			List<NumberPair> temp1 = adjList.get(firstNode - 1);
			List<NumberPair> temp2 = adjList.get(secondNode - 1);
			temp1.add(new NumberPair(secondNode, weight));
			temp2.add(new NumberPair(firstNode, weight));
			if (firstNode < secondNode) {
				NumberTriple edge = new NumberTriple(firstNode, secondNode, weight);
				edgeList.add(edge);
			} else {
				NumberTriple edge = new NumberTriple(secondNode, firstNode, weight);
				edgeList.add(edge);
			}
		}

		// Using Prim's algo to create MST
		// prim(sc.nextInt());

		// Using Kruskal's algo to create MST
		kruskal();

		System.out.println(weight);
		for (NumberTriple edge : edgeList) {
			System.out.println(edge);
		}
	}

	// PriorityQueue ADT - builds MST using a given node as the starting node
	private void prim(int root) {
		PriorityQueue<NumberTriple> pq = new PriorityQueue<>();
		ArrayList<NumberTriple> edges = new ArrayList<>();
		List<NumberPair> lst = adjList.get(root - 1);
		for (NumberPair np : lst) {
			NumberTriple nt = new NumberTriple(root, np.getNode(), np.getWeight());
			pq.add(nt);
		}
		visited[root - 1] = true;
		while (!pq.isEmpty()) {
			NumberTriple temp = pq.poll();
			final int secondTemp = temp.getSecondNode() - 1;
			if (!visited[secondTemp]) {
				visited[secondTemp] = true;
				edges.add(temp);
				weight += temp.getWeight();
				List<NumberPair> lstTemp = adjList.get(secondTemp);
				for (NumberPair np : lstTemp) {
					NumberTriple nt = new NumberTriple(secondTemp + 1, np.getNode(), np.getWeight());
					pq.add(nt);
				}
			}
		}
		edgeList = edges;
	}

	// PriorityQueue ADT - builds MST using weight of edges
	private void kruskal() {
		ArrayList<NumberTriple> edges = new ArrayList<>();
		PriorityQueue<NumberTriple> pq = new PriorityQueue<>(edgeList);
		NumberTriple first = pq.poll();
		int firstNode = first.getFirstNode() - 1;
		int secondNode = first.getSecondNode() - 1;
		visited[firstNode] = true;
		visited[secondNode] = true;
		edges.add(first);
		weight += first.getWeight();
		while (!pq.isEmpty()) {
			NumberTriple temp = pq.poll();
			final int secondTemp = temp.getSecondNode() - 1;
			if (!visited[secondTemp]) {
				visited[secondTemp] = true;
				edges.add(temp);
				weight += temp.getWeight();
			}
		}
		edgeList = edges;
	}

	public static void main(String[] args) {
		MST mst = new MST();
		mst.run();
	}

}
