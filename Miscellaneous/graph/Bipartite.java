package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/* 
 * Checks to see if vertices can be split into 2 distinct groups with each vertex only having
 * edges pointing to vertices in the other group. Can be tested in directed/undirected
 * weighted/unweighted graphs. Illustrated using directed graphs in this specific scenario.
 * Can simple BFS/DFS algo to cycle through the vertices.
 * */
public class Bipartite {
	private ArrayList<LinkedList<NumberPair>> adjList;
	private int[] parent;
	private int[] colour;
	private int numVertices;
	private boolean[] visited;
	private boolean tof;
	private boolean search;

	private void run() {
		Scanner sc = new Scanner(System.in);
		numVertices = sc.nextInt();
		adjList = new ArrayList<>();
		parent = new int[numVertices];
		colour = new int[numVertices];
		visited = new boolean[numVertices];

		// Initializing LinkedLists in adjList and parent[i] = -1
		for (int i = 0; i < numVertices; ++i) {
			adjList.add(new LinkedList<NumberPair>());
			parent[i] = -1;
		}

		// Input for edges
		for (int i = 0; i < numVertices - 1; ++i) {
			int firstNode = sc.nextInt();
			int secondNode = sc.nextInt();
			NumberPair np = new NumberPair(secondNode, 1);
			adjList.get(firstNode).add(np);
		}

		printResults();
	}

	// Stack ADT to save memory space from enqueueing all the vertices
	private boolean dfs(int i) {
		Stack<Integer> stack = new Stack<>();
		if (visited[i]) {
			return tof;
		}
		stack.push(i);
		while (!stack.isEmpty()) {
			int currNode = stack.pop();
			visited[currNode] = true;
			if (colour[currNode] == 0) {
				if (parent[currNode] == -1) {
					colour[currNode] = 1;
				} else {
					colour[currNode] = -1 * colour[parent[currNode]];
				}
				LinkedList<NumberPair> lst = adjList.get(currNode);
				for (NumberPair np : lst) {
					int node = np.getNode();
					parent[node] = currNode;
					stack.push(node);
				}
			} else {
				if (colour[currNode] != -1 * colour[parent[currNode]]) {
					return false;
				}
			}
		}
		return true;
	}

	private void printResults() {
		if (!search) {
			for (int i = 0; i < numVertices; ++i) {
				tof = dfs(i);
				if (!tof) {
					break;
				}
			}
			search = true;
		} else {
		}
		System.out.println(tof);
	}

	public static void main(String args[]) {
		Bipartite bp = new Bipartite();
		bp.run();
	}
}
