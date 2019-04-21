
/*
 * Name       : Yuan Xinran, Stanley
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class PandaChess {
	// Variable declaration
	private int[] indeg;
	private ArrayList<LinkedList<Integer>> adjList;
	private LinkedList<Integer> ranking;
	private int numPlayers, numMatches;
	private boolean noPossible, noUnique;

	private void run() {
		// Variable instantiation
		Scanner sc = new Scanner(System.in);
		numPlayers = sc.nextInt();
		numMatches = sc.nextInt();
		indeg = new int[numPlayers + 1];
		adjList = new ArrayList<>();
		ranking = new LinkedList<>();

		for (int i = 0; i <= numPlayers; ++i) {
			adjList.add(new LinkedList<Integer>());
		}

		for (int i = 0; i < numMatches; ++i) {
			final int first = sc.nextInt();
			final int second = sc.nextInt();
			adjList.get(first).addLast(second);
			++indeg[second];
		}

		kahn();

		if (noPossible) {
			System.out.println("No possible ranking.");
		} else if (noUnique) {
			System.out.println("Ranking is not unique.");
		} else {
			for (int i : ranking) {
				System.out.println(i);
			}
		}
	}

	private void kahn() {
		LinkedList<Integer> queue = new LinkedList<>();
		noPossible = false;
		for (int i = 1; i < indeg.length; ++i) {
			if (indeg[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			if (queue.size() > 1) {
				noUnique = true;
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
		if (ranking.size() != numPlayers) {
			noPossible = true;
		}
	}

	public static void main(String[] args) {
		PandaChess newPandaChess = new PandaChess();
		newPandaChess.run();
	}
}
