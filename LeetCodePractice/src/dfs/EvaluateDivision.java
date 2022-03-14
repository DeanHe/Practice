package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
#399
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

analysis:
TC: O(V + E) which is same for any DFS over a graph algorithm
*/
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            String u = eq.get(0);
            String v = eq.get(1);
            graph.computeIfAbsent(u, x -> new HashMap<>()).put(v, values[i]);
            graph.computeIfAbsent(v, x -> new HashMap<>()).put(u, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String end = query.get(1);
            res[i] = dfs(start, end, graph, new HashSet<>());
        }
        return res;
    }

    private double dfs(String start, String end, Map<String, Map<String, Double>> graph, Set<String> visited) {
        if (visited.contains(start)) {
            return -1.0;
        }
        if (!graph.containsKey(start)) {
            return -1.0;
        }
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        visited.add(start);
        Map<String, Double> neighbors = graph.get(start);
        double res = -1.0;
        for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
            String nb = entry.getKey();
            double multiple = entry.getValue();
            double next = dfs(nb, end, graph, visited);
            if (next != -1.0) {
                res = multiple * next;
                break;
            }
        }
        visited.remove(start);
        return res;
    }
}
