package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
public class EvaluateDivision {
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		HashMap<String, ArrayList<String>> pairs = new HashMap<>();
		HashMap<String, ArrayList<Double>> valuePairs = new HashMap<>();
		for(int i = 0; i < equations.length; i++) {
			String[] eq = equations[i];
			if(!pairs.containsKey(eq[0])) {
				pairs.put(eq[0], new ArrayList<>());
				valuePairs.put(eq[0], new ArrayList<>());				
			}
			if(!pairs.containsKey(eq[1])) {
				pairs.put(eq[1], new ArrayList<>());
				valuePairs.put(eq[1], new ArrayList<>());				
			}
			pairs.get(eq[0]).add(eq[1]);
			pairs.get(eq[1]).add(eq[0]);
			valuePairs.get(eq[0]).add(values[i]);
			valuePairs.get(eq[1]).add(1 / values[i]);
		}
		double[] res = new double[queries.length];
		for(int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			String start = query[0];
			String end = query[1];
			HashSet<String> visited = new HashSet<>();
			res[i] = dfs(pairs, valuePairs, visited, start, end, 1.0);
		}
		return res;
	}
	private double dfs(HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> valuePairs, HashSet<String> visited, String start, String end, double currentValue) {
		if(visited.contains(start)) {
			return 0.0;
		}
		if(!pairs.containsKey(start)) {
			return 0.0;
		}
		if(start.contains(end)) {
			return currentValue;
		}
		visited.add(start);
		ArrayList<String> neighbors = pairs.get(start);
		double temp = 0.0;
		for(int i = 0; i < neighbors.size(); i++) {
			String nb = neighbors.get(i);
			double nb_value = valuePairs.get(start).get(i);
			temp = dfs(pairs, valuePairs, visited, nb, end, currentValue * nb_value);
			if(temp != 0.0) {
				break;
			}
		}
		visited.remove(start);
		return temp;
	}
}
