package UnionFind;

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
		HashSet<Integer> rootSet = new HashSet<>(parent.values());
		for (int root : rootSet) {
			ArrayList<Node> ls = new ArrayList<>();
			for (Node node : list) {
				if (parent.get(node.id1) == root) {
					ls.add(node);
				}
			}
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
			groups.put(root, ls);
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
				for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
					if (entry.getValue() == root_id2) {
						entry.setValue(root_id1);
					}
				}
			} else {
				parent.put(node.id2, root_id1);
			}
		} else {
			if (parent.containsKey(node.id2)) {
				int root_id2 = parent.get(node.id2);
				parent.put(node.id1, root_id2);
			} else {
				parent.put(node.id1, node.id1);
				parent.put(node.id2, node.id1);
			}
		}
	}
}
