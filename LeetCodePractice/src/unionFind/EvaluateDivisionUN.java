package unionFind;
/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

import java.util.*;
import java.util.Map;

public class EvaluateDivisionUN {
	private Map<String, String> parent = new HashMap<>();
	private Map<String, Double> dist = new HashMap<>();

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		int len = values.length;
		double[] res = new double[queries.size()];
		for (int i = 0; i < len; i++) {
			String nu = equations.get(i).get(0);
			String de = equations.get(i).get(1);
			String startRoot = findRoot(nu);
			String endRoot = findRoot(de);
			if (!startRoot.equals(endRoot)) {
				parent.put(startRoot, endRoot);
				dist.put(startRoot, dist.get(de) * values[i] / dist.get(nu));
			}
		}
		for (int i = 0; i < queries.size(); i++) {
			String nu = queries.get(i).get(0);
			String de = queries.get(i).get(1);
			if (!parent.containsKey(nu) || !parent.containsKey(de)) {
				res[i] = -1.0;
				continue;
			}
			String startRoot = findRoot(nu);
			String endRoot = findRoot(de);
			if (!startRoot.equals(endRoot)) {
				res[i] = -1.0;
			} else {
				res[i] = dist.get(nu) / dist.get(de);
			}
		}
		return res;
	}

	private String findRoot(String s) {
		if (!parent.containsKey(s)) {
			parent.put(s, s);
			dist.put(s, 1.0);
			return s;
		}
		String root = s;
		double val = 1.0;
		while (!parent.get(root).equals(root)) {
			String father = parent.get(root);
			val *= dist.get(root);
			root = father;
		}
		parent.put(s, root);
		dist.put(s, val);
		return root;
	}
}
