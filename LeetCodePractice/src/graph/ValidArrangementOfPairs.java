package graph;

import java.util.*;

/*
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
Return any valid arrangement of pairs.
Note: The inputs will be generated such that there exists a valid arrangement of pairs.

Example 1:

Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3
Example 2:

Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
Example 3:

Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2


Constraints:

1 <= pairs.length <= 10^5
pairs[i].length == 2
0 <= starti, endi <= 10^9
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.

hint:
1 Could you convert this into a graph problem?
2 Consider the pairs as edges and each number as a node.
3 We have to find an Eulerian path of this graph. Hierholzer’s algorithm can be used.

analysis:
Eulerian Path, which is a path that walks through each edge of the graph exactly once.
https://leetcode.com/problems/valid-arrangement-of-pairs/discuss/1611978/C%2B%2B-Eulerian-Path-with-Explanation

Time Complexity: O(m+n), where m == edges, and n is the number of nodes in the graph (the number of distinct numbers in pairs)
Space Complexity: O(m+n)
 */
public class ValidArrangementOfPairs {
    public int[][] validArrangement(int[][] pairs) {
        int len = pairs.length;
        Map<Integer, Integer> inDeg = new HashMap<>();
        Map<Integer, Integer> outDeg = new HashMap<>();
        Map<Integer, Queue<Integer>> graph = new HashMap<>();
        for(int[] p : pairs){
            int from = p[0];
            int to = p[1];
            inDeg.put(to, inDeg.getOrDefault(to, 0) + 1);
            outDeg.put(from, outDeg.getOrDefault(from, 0) + 1);
            graph.computeIfAbsent(from, x -> new LinkedList<>()).add(to);
        }
        int start = -1;
        for(int i : graph.keySet()){
            if(outDeg.get(i) - inDeg.getOrDefault(i, 0) == 1){
                start = i;
            }
            if(start == -1){
                // exists Eulerian Circuit -> can start at any node
                start = outDeg.keySet().stream().findFirst().get();
            }
        }
        List<int[]> ls = new ArrayList<>();
        dfs(ls, start, graph);
        Collections.reverse(ls);
        int[][] res = new int[len][2];
        for(int i = 0; i < len; i++){
            res[i] = ls.get(i);
        }
        return res;
    }

    private void dfs(List<int[]> ls, int from, Map<Integer, Queue<Integer>> graph) {
        Queue<Integer> nbs = graph.getOrDefault(from, new LinkedList<>());
        while(!nbs.isEmpty()){
            // remove after visited
            int to = nbs.poll();
            dfs(ls, to, graph);
            // postorder
            ls.add(new int[]{from, to});
        }
    }
}
