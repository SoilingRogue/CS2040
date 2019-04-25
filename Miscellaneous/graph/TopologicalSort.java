package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TopologicalSort {
	private boolean noPossible, noUnique;
	private int[] indeg;
	private ArrayList<LinkedList<Integer>> adjList;
	private LinkedList<Integer> ranking;
	private int numVertices, numEdges;

	private void run() {
		Scanner sc = new Scanner(System.in);
		numVertices = sc.nextInt();
		numEdges = sc.nextInt();
		indeg = new int[numVertices];
		adjList = new ArrayList<>();
		ranking = new LinkedList<>();

		for (int i = 0; i < numVertices; ++i) {
			adjList.add(new LinkedList<Integer>());
		}

		// Process all edges - updates indeg[] and adjList
		for (int i = 0; i < numEdges; ++i) {
			// Directed edge from first node to second
			final int first = sc.nextInt();
			final int second = sc.nextInt();
			// Updates adjList
			adjList.get(first).addLast(second);
			// Increment indeg for node with incoming edge
			++indeg[second];
		}

		dfs();
		kahn();

		if (noPossible) { // Cycle
			System.out.println("No possible ranking.");
		} else if (noUnique) { // Multiple nodes with same ranking
			System.out.println("No unique ranking.");
		} else {
			for (int i : ranking) {
				System.out.println(i);
			}
		}
	}

	private void dfs() {

	}

	private void kahn() {
		LinkedList<Integer> queue = new LinkedList<>();
		noPossible = false;
		// Add vertices with no incoming edges to the queue - runs in O(V)
		for (int i = 1; i < numVertices; ++i) {
			if (indeg[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			// If the queue contains more than 1 element
			if (queue.size() > 1) {
				noUnique = true; // There are more than 1 possible way to order them
			}
			int curr = queue.removeFirst();
			ranking.addLast(curr);
			for (int neighbour : adjList.get(curr)) {
				--indeg[neighbour];
				if (indeg[neighbour] == 0) {
					queue.addLast(neighbour);
				}
			}
		}

		// If list containing topological order does not contain all vertices - ie cycle
		if (ranking.size() != numVertices) {
			noPossible = true; // No possible topological ordering
		}
	}

	public static void main(String[] args) {
		TopologicalSort ts = new TopologicalSort();
		ts.run();
	}

}
