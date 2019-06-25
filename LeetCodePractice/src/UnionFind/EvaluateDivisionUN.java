package UnionFind;
/*Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.*/

import java.util.HashMap;
import java.util.Map;

public class EvaluateDivisionUN {
	Map<String, String> parent = new HashMap<>();
	Map<String, Double> dist = new HashMap<>();
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		int len = values.length;
		double[] res = new double[len];
		for(int i = 0; i < len; i++){
			String startRoot = findRoot(parent, equations[i][0]);
			String endRoot = findRoot(parent, equations[i][1]);
			if(!startRoot.equals(endRoot)){
				parent.put(startRoot, endRoot);
				dist.put(startRoot, dist.get(endRoot) * values[i] / dist.get(startRoot));
			}
		}
		for(int i = 0; i < queries.length; i++){
			if(!parent.containsKey(queries[i][0]) || !parent.containsKey(queries[i][1])){
				continue;
			}
			String startRoot = findRoot(parent, queries[i][0]);
			String endRoot = findRoot(parent, queries[i][1]);
			if(!startRoot.equals(endRoot)){
				res[i] = -1.0;
				continue;
			}
			res[i] = dist.get(startRoot) / dist.get(endRoot);
		}
	}
	private String findRoot(Map<String, String> parent, Map<String, Double> dist, String s){
		if(!parent.containsKey(s)){
			parent.put(s, s);
			dist.put(s, 1.0);
			return s;
		}
		while(!parent.get(s).equals(s)){
			String father = parent.get(s);
			s = father;
		}
		
	}
	
	private int findRoot(int x) {
		int root = x;
		while (id[root] != root) {
			root = id[root];
		}
		while (id[x] != root) {
			int father = id[x];
			id[x] = root;
			x = father;
		}
		return root;
	}
}
