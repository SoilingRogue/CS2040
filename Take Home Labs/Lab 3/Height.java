/*
 * Name       : Yuan Xinran, Stanley 
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class Height {
    // ADT to store adjacency list, can also use
    // ArrayList<HashMap<Integer, Long>>
    private ArrayList<LinkedList<NumberPair>> adjList;
    private boolean[] visited;

    // Stores relative height,
    // index 0 from nodes 0 - 0, index 1 from nodes 0 - 1,
    // index 2 from nodes 0 - 2 .. index i from nodes 0 - i etc
    private long[] memoize;

    private class NumberPair {
        private final int node;
        private final long height;

        public NumberPair(int node, long height) {
            this.node = node;
            this.height = height;
        }

        public long getHeight() {
            return this.height;
        }

        public int getNode() {
            return this.node;
        }
    }

    private void run() {
        // Declaring variables
        Scanner sc = new Scanner(System.in);
        final int numVertices = sc.nextInt();
        adjList = new ArrayList<>();
        visited = new boolean[numVertices];
        memoize = new long[numVertices];

        // Adjacency List
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
        visited[0] = true;
        memoize[0] = 0;
        while (!queue.isEmpty()) {
            int node = queue.removeFirst();
            List<NumberPair> temp = adjList.get(node);
            for (NumberPair np : temp) {
                final int currNode = np.getNode() - 1;
                if (visited[currNode] == false) {
                    memoize[currNode] = np.getHeight() + memoize[node];
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
                    memoize[currNode] = np.getHeight() + memoize[node];
                    stack.push(currNode);
                }
            }
            visited[node] = true;
        }
    }

    public static void main(String[] args) {
        Height newHeight = new Height();
        newHeight.run();
    }
}
