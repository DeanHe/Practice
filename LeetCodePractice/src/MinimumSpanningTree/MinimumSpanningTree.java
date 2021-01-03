package MinimumSpanningTree;

import java.util.*;
import java.util.function.IntPredicate;

import javax.naming.spi.DirStateFactory.Result;

/*
Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them),

Return the connects if can connect all the cities, otherwise return empty list.
Example
Given the connections = [Acity,Bcity,1], [Acity,Ccity,2], [Bcity,Ccity,3]

Return [Acity,Bcity,1], [Acity,Ccity,2]

Notice
Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.
Kruskal's Algorithm
*/
public class MinimumSpanningTree {
	class Connection {
		String city1, city2;
		int cost;

		public Connection(String city1, String city2, int cost) {
			this.city1 = city1;
			this.city2 = city2;
			this.cost = cost;
		}
	}

	/**
	 * @param connections
	 *            given a list of connections include two cities and cost
	 * @return a list of connections from results
	 */
	public List<Connection> lowestCost(List<Connection> connections) {
		Collections.sort(connections, (a, b) -> {
			if (a.cost != b.cost) {
				return a.cost - b.cost;
			}
			if (a.city1.equals(b.city1)) {
				return a.city2.compareTo(b.city2);
			}
			return a.city1.compareTo(b.city1);
		});
		// city: group id in UN
		Map<String, Integer> groupMap = new HashMap<>();
		int n = 0;
		for (Connection c : connections) {
			if (!groupMap.containsKey(c.city1)) {
				groupMap.put(c.city1, n++);
			}
			if (!groupMap.containsKey(c.city2)) {
				groupMap.put(c.city2, n++);
			}
		}
		int[] parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		ArrayList<Connection> res = new ArrayList<>();
		for (Connection c : connections) {
			// check if the connection forms a cycle with the spanning tree
			// formed so far.
			// use union find to getRoot and union
			int id1 = groupMap.get(c.city1);
			int id2 = groupMap.get(c.city2);
			int root1 = getRoot(id1, parent);
			int root2 = getRoot(id2, parent);
			if (root1 != root2) {
				parent[root1] = root2;
				res.add(c);
			}
		}
		if (res.size() != n - 1) {
			return new ArrayList<>();
		}
		return res;
	}

	private int getRoot(int i, int[] parent) {
		while (parent[i] != i) {
			i = parent[i];
		}
		return i;
	}
}
