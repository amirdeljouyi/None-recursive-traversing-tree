import java.util.*;

public class Main {
	static Node root;

	public static void main(String[] args) {
		root = new Node(5);
		System.out.println("Binary Tree Example:");
		System.out.println("Building tree with root value " + root.value);
		insert(root, 1);
		insert(root, 8);
		insert(root, 6);
		insert(root, 3);
		insert(root, 9);
		System.out.println("\nTraversing tree in preorder:");
		preorder(root);
		System.out.println("\nTraversing tree in postorder:");
		postorder(root);
	}

	static class Node {
		Node left;

		Node right;

		int value;

		public Node(int value) {
			this.value = value;
		}
	}

	public static void preorder(Node t) {
		Stack<Node> st = new Stack<Node>();
		int tag = 0, i = 0;
		while (true) {
			st.push(t);
			System.out.print(t.value);
			System.out.print(",");

			// for down
			if (t.left != null) {
				t = t.left;
				tag = 0;
				continue;
			}
			if (t.right != null) {
				t = t.right;
				tag = 1;
				i++;
				continue;
			}
			// for up
			if (tag == 0) {
				st.pop();
				t = st.lastElement().right;
				tag = 1;
				i++;
			} else {
				int counter = 0;
				while (counter < i + 1) {
					st.pop();
					counter++;
				}
				if (st.isEmpty()) {
					break;
				} else {
					t = st.lastElement().right;
					tag = 1;
					i = 1;
				}
			}
		}
	}

	public static void postorder(Node t) {
		Stack<Node> st = new Stack<Node>();
		int tag = 0;
		int i = 0;
		while (true) {
			st.push(t);
			// for down
			while (t.left != null) {
				t = t.left;
				st.push(t);
				tag = 0;
			}
			if (t.right != null) {
				t = t.right;
				i++;
				tag = 1;
				continue;
			}
			// for up
			if (tag == 0) {
				System.out.print(t.value + ",");
				st.pop();
				t = st.lastElement().right;
				i++;
				tag = 1;
			} else {
				System.out.print(t.value + ",");
				int counter = 0;
				while (counter < i) {
					st.pop();
					t = st.lastElement();
					System.out.print(t.value + ",");
					counter++;
				}
				st.pop();
				if (st.isEmpty()) {
					break;
				} else {
					t = st.lastElement().right;
					i = 1;
					tag = 1;
				}
			}

		}
	}

	public static Node parent(Node root, Node child) {
		if (root == null) {
			return null;
		}
		if (root == child) {
			return null;
		}
		Stack<Node> st = new Stack<Node>();
		Node t = root;
		int tag = 0;
		int i = 0;
		while (t.left != child && t.right != child) {
			// for down
			st.push(t);
			if (t.left != null) {
				t = t.left;
				tag = 0;
				continue;
			} else if (t.right != null) {
				t = t.right;
				tag = 1;
				i++;
				continue;
			}
			// for up
			if (tag == 0) {
				st.pop();
				t = st.lastElement().right;
				tag = 1;
				i++;
			} else {
				int counter = 0;
				while (counter < i + 1) {
					st.pop();
					counter++;
				}
				if (st.isEmpty()) {
					break;
				} else {
					t = st.lastElement().right;
					tag = 1;
					i = 1;
				}
			}
		}
		return t;
	}

	public static void insert(Node node, int value) {
		if (value < node.value) {
			if (node.left != null) {
				insert(node.left, value);
			} else {
				System.out.println("  Inserted " + value + " to left of "
						+ node.value);
				node.left = new Node(value);
				System.out.print("  Parent :");
				System.out.println(parent(root, node.left).value);
			}
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
				System.out.println("  Inserted " + value + " to right of "
						+ node.value);
				node.right = new Node(value);
				System.out.print("  Parent :");
				System.out.println(parent(root, node.left).value);
			}
		}
	}

}
