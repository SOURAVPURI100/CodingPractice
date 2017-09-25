
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Node {
	// int num;
	int value;
	List<Node> edges;

	public Node(int value) {
		this.value = value;
		this.edges = new ArrayList<>();
	}
}

// class Edge{
// Node dest;
// }

class test {

	static int output = 0;

	public static void main(String args[]) throws Exception {
		/*
		 * Read input from stdin and provide input before running Use either of
		 * these methods for input
		 * 
		 */
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();

		for (int i = 0; i < N; i++) {
			List<Node> list = new ArrayList<>();
			output = 0;
			int nodes = s.nextInt();
			int k = s.nextInt();

			for (int j = 0; j < nodes; j++) {

				Node node = new Node(s.nextInt());

				list.add(node);

			}
			// Edges Formation
			for (int j = 0; j < nodes - 1; j++) {

				Node node1 = list.get(s.nextInt() - 1);
				Node node2 = list.get(s.nextInt() - 1);

				List<Node> listTemp = node1.edges;
				listTemp.add(node2);

			}

			for (int j = 0; j < list.size(); j++) {
				System.out.println("abc");
				Set<Integer> set = new HashSet<>();
				calc(list.get(j), set, k);
			}

			System.out.println("Case " + i + ":" + output);

		}
	}

	// Traverse sub-tree from here

	public static void calc(Node node, Set<Integer> set, int k) {

		Set<Integer> tempSet = new HashSet<>(set);

		tempSet.add(node.value);

		if (tempSet.size() <= k) {
			output++;
		}

		if (node.edges == null)
			return;

		for (int j = 0; j < node.edges.size(); j++) {

			calc(node.edges.get(j), tempSet, k);
		}

	}

}
