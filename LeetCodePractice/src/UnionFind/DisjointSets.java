package UnionFind;
//A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets. 
//A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
import java.util.*;
/* test input
7
1,2
4,3
2,3
6,7
4,5
5,6
8,9

6
1,2
2,3
3,4
5,6
6,7
8,9

7
1,2
4,3
2,3
4,2
10,8
8,6
5,7
 */

class Node {
	int id1, id2;

	Node(int id1, int id2) {
		this.id1 = id1;
		this.id2 = id2;
	}
}

public class DisjointSets {
	// id : root
	HashMap<Integer, Integer> parent = new HashMap<>();
	// root : nodes
	HashMap<Integer, ArrayList<Node>> groups = new HashMap<>();

	public static void main(String[] args) throws Exception {

		ArrayList<Node> list = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		int total = Integer.valueOf(input.nextLine());
		for (int i = 0; i < total; i++) {
			String nodestr = input.nextLine();
			String[] arr = nodestr.split(",");
			int id1 = Integer.valueOf(arr[0]);
			int id2 = Integer.valueOf(arr[1]);
			Node n = new Node(id1, id2);
			list.add(n);
		}
		DisjointSets test = new DisjointSets();
		test.group(list);
		test.output(list);
	}

	public void group(ArrayList<Node> list) {
		for (Node node : list) {
			union(node);
		}
		for (int root : groups.keySet()) {
			ArrayList<Node> ls = groups.get(root);
			Collections.sort(ls, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					if (o1.id1 != o2.id1) {
						return o1.id1 - o2.id1;
					} else {
						return o1.id2 - o2.id2;
					}
				}
			});
		}
	}

	public void output(ArrayList<Node> list) {
		for (int root : groups.keySet()) {
			ArrayList<Node> ls = groups.get(root);
			for (Node node : ls) {
				System.out.print(node.id1 + "," + node.id2 + " ");
			}
			System.out.println();
		}
	}

	public void union(Node node) {
		if (parent.containsKey(node.id1)) {
			int root_id1 = parent.get(node.id1);
			if (parent.containsKey(node.id2)) {
				int root_id2 = parent.get(node.id2);
				if (root_id1 != root_id2) {
					ArrayList<Node> root_id2_list = groups.get(root_id2);
					for (Node n2 : root_id2_list) {
						parent.put(n2.id1, root_id1);
						parent.put(n2.id2, root_id1);
						groups.get(root_id1).add(n2);
					}
					groups.remove(root_id2);
				}
			} else {
				parent.put(node.id2, root_id1);
			}
			groups.get(root_id1).add(node);
		} else {
			if (parent.containsKey(node.id2)) {
				int root_id2 = parent.get(node.id2);
				parent.put(node.id1, root_id2);
				groups.get(root_id2).add(node);
			} else {
				parent.put(node.id1, node.id1);
				parent.put(node.id2, node.id1);
				groups.put(node.id1, new ArrayList<>());
				groups.get(node.id1).add(node);
			}
		}
	}
}
