
/*
 * Name       : Yuan Xinran, Stanley
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class PandaIslands {
	// Variable declaration
	private ArrayList<LinkedList<Integer>> adjList;
	private int[] colour, area;
	private int numIsland, numBridges;

	private void run() {
		// Variable instantiation - Start from index 1 to N
		Scanner sc = new Scanner(System.in);
		numIsland = sc.nextInt();
		numBridges = sc.nextInt();
		colour = new int[numIsland + 1];
		area = new int[numIsland + 1];
		adjList = new ArrayList<>();
		for (int i = 0; i <= numIsland; ++i) {
			LinkedList<Integer> temp = new LinkedList<>();
			adjList.add(temp);
		}

		// Processing input - Storing island area and bridges
		for (int i = 1; i <= numIsland; ++i) {
			area[i] = sc.nextInt();
		}

		for (int i = 0; i < numBridges; ++i) {
			final int first = sc.nextInt();
			final int second = sc.nextInt();
			adjList.get(first).addLast(second);
			adjList.get(second).addLast(first);
		}

		System.out.println(findArea());
	}

	private int findArea() {
		int blackArea = 0;
		for (int i = 1; i <= numIsland; ++i) {
			if (colour[i] == 0) {
				// System.out.println("Island:" + i);
				blackArea += bfs(i);
				// System.out.println("blackarea:" + blackArea);
			}
		}
		return blackArea;
	}

	private int bfs(int i) {
		int white = 0;
		int black = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(i);
		colour[i] = 1;
		while (!queue.isEmpty()) {
			int curr = queue.removeFirst();
			if (colour[curr] == 1) {
				black += area[curr];
			} else {
				white += area[curr];
			}
			LinkedList<Integer> temp = adjList.get(curr);
			for (int next : temp) {
				if (colour[next] == 0) {
					colour[next] = -1 * colour[curr];
					queue.addLast(next);
				}
			}
		}
		// System.out.println("white:" + white + " black:" + black);
		return Math.min(white, black);
	}

	public static void main(String[] args) {
		PandaIslands newPandaIslands = new PandaIslands();
		newPandaIslands.run();
	}
}
