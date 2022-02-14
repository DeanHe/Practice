package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
N objects 1 -> N in a 2-D grid. Given a set of rules as <first, second, left/right/up/down> (the first is on left/right/up/down of the second).
Return any one of possible arrangement of those N objects.
Example: N=3, <1, 3, right>, <2, 3, right>, <1, 2, up>, <1, 2, left>, <2, 3, up>.
one possible result:["*1*", "**2", "3**"], * is empty.

analysis:
2 dimension topological sort
 */
public class ArrangeObjectGoogle {
    int N;

    public int[][] arange(int N, List<String[]> rules) {
        this.N = N;
        int[][] res = new int[N][N];
        // oriented from left to right
        int[] colIndeg = new int[N + 1];
        Map<Integer, List<Integer>> rowGraph = new HashMap<>();
        // oriented from up to bottom
        int[] rowIndeg = new int[N + 1];
        Map<Integer, List<Integer>> colGraph = new HashMap<>();
        for (String[] rule : rules) {
            if (rule[2].equals("left")) {
                int from = Integer.valueOf(rule[0]);
                int to = Integer.valueOf(rule[1]);
                colGraph.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
                colIndeg[to]++;
            } else if (rule[2].equals("right")) {
                int from = Integer.valueOf(rule[1]);
                int to = Integer.valueOf(rule[0]);
                colGraph.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
                colIndeg[to]++;
            } else if (rule[2].equals("up")) {
                int from = Integer.valueOf(rule[0]);
                int to = Integer.valueOf(rule[1]);
                rowGraph.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
                rowIndeg[to]++;
            } else if (rule[2].equals("down")) {
                int from = Integer.valueOf(rule[1]);
                int to = Integer.valueOf(rule[0]);
                rowGraph.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
                rowIndeg[to]++;
            }
        }
        List<Integer> colTopo = topologicalSort(colIndeg, colGraph);
        List<Integer> rowTopo = topologicalSort(rowIndeg, rowGraph);
        for(int r = 0; r < N; r++){
            int val = rowTopo.get(r);
            int c = colTopo.indexOf(val);
            res[r][c] = val;
        }
        return res;
    }

    private List<Integer> topologicalSort(int[] indeg, Map<Integer, List<Integer>> graph) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            if(graph.containsKey(cur)){
                for (int nb : graph.get(cur)) {
                    indeg[nb]--;
                    if (indeg[nb] == 0) {
                        q.offer(nb);
                    }
                }
            }
        }
        return res;
    }
}
