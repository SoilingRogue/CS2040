import java.util.*;

// barebones implementation of BST

// Every vertex in this BST is a Java Class
class BSTVertex {
	BSTVertex(int v) {
		key = v;
		parent = left = right = null;
		height = 0;
		size = 1;
	}

	// all these attributes remain public to slightly simplify the code
	public BSTVertex parent, left, right;
	public int key, size;
	public int height; // will be used in lecture on AVL
}

// This is just a sample implementation
// There are other ways to implement BST concepts...
public class BST {
	public BSTVertex root;

	public BST() {
		root = null;
	}

	// public method called to search for a value v.
	// Return v if it is found in the BST otherwise return -1.
	// Here the assumption is that -1 is never a valid key value.
	public int search(int v) {
		BSTVertex res = search(root, v);
		return res == null ? -1 : res.key;
	}

	// helper method to perform search
	private BSTVertex search(BSTVertex T, int v) {
		if (T == null)
			return null; // not found
		else if (T.key == v)
			return T; // found
		else if (T.key < v)
			return search(T.right, v); // search to the right
		else
			return search(T.left, v); // search to the left
	}

	// public method called to find Minimum key value in BST
	public int findMin() {
		return findMin(root);
	}

	// helper method to perform findMin
	// Question: What happens if BST is empty?
	private int findMin(BSTVertex T) {
		if (T.left == null)
			return T.key; // this is the min
		else
			return findMin(T.left); // go to the left
	}

	// public method called to find Maximum key value in BST
	public int findMax() {
		return findMax(root);
	}

	// helper method to perform findMax
	// Question: Again, what happens if BST is empty?
	private int findMax(BSTVertex T) {
		if (T.right == null)
			return T.key; // this is the max
		else
			return findMax(T.right); // go to the right
	}

	// public method to find successor to given value v in BST.
	public int successor(int v) {
		BSTVertex vPos = search(root, v);
		return vPos == null ? -1 : successor(vPos);
	}

	// helper recursive method to find successor to for a given vertex T in BST
	private int successor(BSTVertex T) {
		if (T.right != null) // this subtree has right subtree
			return findMin(T.right); // the successor is the minimum of right subtree
		else {
			BSTVertex par = T.parent;
			BSTVertex cur = T;
			// if par(ent) is not root and cur(rent) is its right children
			while ((par != null) && (cur == par.right)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par == null ? -1 : par.key; // this is the successor of T
		}
	}

	// public method to find predecessor to given value v in BST
	public int predecessor(int v) {
		BSTVertex vPos = search(root, v);
		return vPos == null ? -1 : predecessor(vPos);
	}

	// helper recursive method to find predecessor to for a given vertex T in BST
	private int predecessor(BSTVertex T) {
		if (T.left != null) // this subtree has left subtree
			return findMax(T.left); // the predecessor is the maximum of left subtree
		else {
			BSTVertex par = T.parent;
			BSTVertex cur = T;
			// if par(ent) is not root and cur(rent) is its left children
			while ((par != null) && (cur == par.left)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par == null ? -1 : par.key; // this is the successor of T
		}
	}

	// public method called to perform inorder traversal
	public void inorder() {
		inorder(root);
		System.out.println();
	}

	// helper method to perform inorder traversal
	private void inorder(BSTVertex T) {
		if (T == null)
			return;
		inorder(T.left); // recursively go to the left
		System.out.printf(" %d", T.key); // visit this BST node
		inorder(T.right); // recursively go to the right
	}

	// public method called to insert a new key with value v into BST
	public void insert(int v) {
		root = insert(root, v);
	}

	// helper recursive method to perform insertion of new vertex into BST
	private BSTVertex insert(BSTVertex T, int v) {
		if (T == null)
			return new BSTVertex(v); // insertion point is found

		if (T.key < v) { // search to the right
			T.right = insert(T.right, v);
			T.right.parent = T;
		} else { // search to the left
			T.left = insert(T.left, v);
			T.left.parent = T;
		}

		updateHeight(T);

		int bfVertex = balanceFactor(T);
		if (bfVertex == 2) {
			int bfLeft = balanceFactor(T.left);
			if (bfLeft == 1) { // bf(v) == 2 && bf(v.left) == 1
				T = rotateRight(T);
			} else { // bf(v) == 2 && bf(v.left) == -1
				T.left = rotateLeft(T.left);
				T = rotateRight(T);
			}
		} else if (bfVertex == -2) {
			int bfRight = balanceFactor(T.right);
			if (bfRight == 1) { // bf(v) == -2 && bf(v.right) == 1
				T.right = rotateRight(T.right);
				T = rotateLeft(T);
			} else { // bf(v) == -2 && bf(v.right) == -1
				T = rotateLeft(T);
			}
		}

		return T; // return the updated BST
	}

	private BSTVertex rotateLeft(BSTVertex v) {
		// reassigning attributes
		BSTVertex rightChild = v.right;
		rightChild.parent = v.parent;
		v.parent = rightChild;
		v.right = rightChild.left;

		if (rightChild.left != null) {
			rightChild.left.parent = v;
		}

		rightChild.left = v;

		// update height of v and rightChild
		updateHeight(v);
		updateHeight(rightChild);

		return rightChild;
	}

	private BSTVertex rotateRight(BSTVertex v) {
		BSTVertex leftChild = v.left;
		leftChild.parent = v.parent;
		v.parent = leftChild;
		v.left = leftChild.right;

		if (leftChild.right != null) {
			leftChild.right.parent = v;
		}

		leftChild.right = v;

		// update height of v and leftChild
		updateHeight(v);
		updateHeight(leftChild);

		return leftChild;
	}

	private int balanceFactor(BSTVertex v) {
		BSTVertex leftChild = v.left;
		BSTVertex rightChild = v.right;
		if (leftChild == null && rightChild == null) {
			return 0;
		} else if (leftChild == null) {
			return -1 * rightChild.height;
		} else if (rightChild == null) {
			return leftChild.height;
		}
		return leftChild.height - rightChild.height;
	}

	private void updateHeight(BSTVertex v) {
		BSTVertex leftChild = v.left;
		BSTVertex rightChild = v.right;
		if (leftChild == null && rightChild == null) {
			v.height = 0;
			v.size = 1;
		} else if (leftChild == null) {
			v.height = rightChild.height + 1;
			v.size = rightChild.size + 1;
		} else if (rightChild == null) {
			v.height = leftChild.height + 1;
			v.size = leftChild.size + 1;
		} else {
			v.height = Math.max(leftChild.height, rightChild.height) + 1;
			v.size = leftChild.size + rightChild.size + 1;
		}
	}

	public int getRank(int v) {
		return getRank(root, v);
	}

	private int getRank(BSTVertex T, int v) {
		if (T.key < v) {
			// System.out.println("vertex" + T.key + "size" + T.size);
			if (T.left == null)
				return 1 + getRank(T.right, v);
			return T.left.size + 1 + getRank(T.right, v);
		} else if (T.key > v) {
			// System.out.println("vertex" + T.key + "size" + T.size);
			return getRank(T.left, v);
		}

		// System.out.println("vertex" + T.key + "size" + T.size);
		// T.key = v;
		if (T.left == null)
			return 1;
		return T.left.size + 1;
	}

	// public method to delete a vertex containing key with value v from BST
	public void delete(int v) {
		root = delete(root, v);
	}

	// helper recursive method to perform deletion
	private BSTVertex delete(BSTVertex T, int v) {
		if (T == null)
			return T; // cannot find the item to be deleted

		if (T.key < v) // search to the right
			T.right = delete(T.right, v);
		else if (T.key > v) // search to the left
			T.left = delete(T.left, v);
		else { // this is the node to be deleted
			if (T.left == null && T.right == null) // this is a leaf
				T = null; // simply erase this node
			else if (T.left == null && T.right != null) { // only one child at right
				T.right.parent = T.parent;
				T = T.right; // bypass T
			} else if (T.left != null && T.right == null) { // only one child at left
				T.left.parent = T.parent;
				T = T.left; // bypass T
			} else { // has two children, find successor
				int successorV = successor(v);
				T.key = successorV; // replace this key with the successor's key
				T.right = delete(T.right, successorV); // delete the old successorV
			}
		}

		return T; // return the updated BST
	}

	// will be used in AVL lecture
	private int getHeight(BSTVertex T) {
		if (T == null)
			return -1;
		else
			return Math.max(getHeight(T.left), getHeight(T.right)) + 1;
	}

	public int getHeight() {
		return getHeight(root);
	}
}
