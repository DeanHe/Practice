package DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class EvaluateDivision {
    Map<String, Map<String, Double>> graph;
    Set<String> visited;

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] eq = equations[i];
            String u = eq[0];
            String v = eq[1];
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1.0 / values[i]);
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String start = query[0];
            String end = query[1];
            res[i] = dfs(start, end, new HashSet<>());
        }
        return res;
    }

    private double dfs(String start, String end, Set<String> visited) {
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
        for (String nb : neighbors.keySet()) {
            double nb_val = graph.get(start).get(nb);
            double next = dfs(nb, end, visited);
            if (next != -1.0) {
                res = nb_val * next;
                break;
            }
        }
        visited.remove(start);
        return res;
    }
}
