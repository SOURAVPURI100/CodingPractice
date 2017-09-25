package com.practice;

class Node {
	int val;
	Node left;
	Node right;
}

public class BLT {

	public static void main(String[] args) {
		String str = "1 2 3 # # 4 # # 5";
		int n = 9;
		long val = findMax(n, str);

	}

	static long findMax(int n, String tree) {

		Node[] nodeArray = new Node[n * 5];
		String[] nodes = tree.split(" ");
		if (nodes.length > 0) {
			Node node = new Node();
			node.val = Integer.parseInt(nodes[0]);
			nodeArray[0] = node;
		}
		int i = 0;
		for (int j = 1; j < nodes.length; j++) {

			Node node = nodeArray[i];
			if (nodes[j].equals("#")) {
				// node.left = null;
			} else {
				Node left = new Node();
				left.val = Integer.parseInt(nodes[j]);
				nodeArray[i].left = left;
			}

			if (j + 1 < nodes.length) {
				j++;
				if (nodes[j].equals("#")) {
					// node.right = null;
				} else {
					Node right = new Node();
					right.val = Integer.parseInt(nodes[j]);
					nodeArray[i].right = right;
				}
			}
			i++;
		}
		return 0;
	}

}
