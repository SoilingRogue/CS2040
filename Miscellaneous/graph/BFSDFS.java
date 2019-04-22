package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/* 
 * Basic graphing traversal concepts using BFS - breadth-first search & DFS - depth-first search
 * Concept illustration using height difference between 2 areas
 * **Assumes directed weighted graph**
 * Uses ArrayList<List<NumberPair>> as an AdjacencyList - NumberPair - other Node, Weight
 * Can also use List<NumberTriple> as an EdgeList - NumberTriple - Node 1, Node 2, Weight
 * Or int[][] as an AdjacencyMatrix - arr[i][j] = Weight of Node i to j
 * */
public class BFSDFS {
	/*
	 * ADT to store adjacency list, can also use ArrayList<HashMap<Integer, Long>>
	 */
	private ArrayList<LinkedList<NumberPair>> adjList;
	private boolean[] visited;

	/*
	 * Stores relative height, index 0 from nodes 1 - 1, index 1 from nodes 1 - 2,
	 * index 2 from nodes 1 - 3 .. index i from nodes 1 - i + 1 etc
	 **/
	private long[] memoize;

	private void run() {
		// Declaring variables
		Scanner sc = new Scanner(System.in);
		final int numVertices = sc.nextInt();
		adjList = new ArrayList<>();
		visited = new boolean[numVertices];
		memoize = new long[numVertices];

		// Adjacency List - initializing all LinkedLists in adjList
		for (int i = 0; i < numVertices; ++i) {
			adjList.add(new LinkedList<NumberPair>());
		}

		// Storing input into adjList
		for (int i = 0; i < numVertices - 1; ++i) {
			int firstNode = sc.nextInt();
			int secondNode = sc.nextInt();
			long height = sc.nextLong();
			List<NumberPair> temp1 = adjList.get(firstNode - 1);
			List<NumberPair> temp2 = adjList.get(secondNode - 1);
			temp1.add(new NumberPair(secondNode, -1 * height));
			temp2.add(new NumberPair(firstNode, height));
		}

		// Using DFS to process stored input
		dfs();

		// Using BFS to process stored input
		// bfs();

		// Query and output
		final int numQuery = sc.nextInt();
		for (int i = 0; i < numQuery; ++i) {
			final int firstNode = sc.nextInt();
			final int secondNode = sc.nextInt();
			final long height = memoize[firstNode - 1] - memoize[secondNode - 1];
			System.out.println(height);
		}
	}

	// Queue ADT to simulate BFS
	public void bfs() {
		final LinkedList<Integer> queue = new LinkedList<>();
		queue.add(0);
		memoize[0] = 0;
		while (!queue.isEmpty()) {
			int node = queue.removeFirst();
			List<NumberPair> temp = adjList.get(node);
			for (NumberPair np : temp) {
				final int currNode = np.getNode() - 1;
				if (!visited[currNode]) {
					memoize[currNode] = np.getWeight() + memoize[node];
					queue.addLast(currNode);
				}
			}
			visited[node] = true;
		}
	}

	// Stack ADT to simulate DFS
	public void dfs() {
		final Stack<Integer> stack = new Stack<>();
		stack.push(0);
		visited[0] = true;
		memoize[0] = 0;
		while (!stack.isEmpty()) {
			int node = stack.pop();
			List<NumberPair> temp = adjList.get(node);
			for (NumberPair np : temp) {
				final int currNode = np.getNode() - 1;
				if (visited[currNode] == false) {
					memoize[currNode] = np.getWeight() + memoize[node];
					stack.push(currNode);
				}
			}
			visited[node] = true;
		}
	}

	public static void main(String[] args) {
		BFSDFS bfsdfs = new BFSDFS();
		bfsdfs.run();
	}
}
