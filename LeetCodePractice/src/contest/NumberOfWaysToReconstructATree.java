package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
You are given an array pairs, where pairs[i] = [xi, yi], and:

There are no duplicates.
xi < yi
Let ways be the number of rooted trees that satisfy the following conditions:

The tree consists of nodes whose values appeared in pairs.
A pair [xi, yi] exists in pairs if and only if xi is an ancestor of yi or yi is an ancestor of xi.
Note: the tree does not have to be a binary tree.
Two ways are considered to be different if there is at least one node that has different parents in both ways.

Return:

0 if ways == 0
1 if ways == 1
2 if ways > 1
A rooted tree is a tree that has a single root node, and all edges are oriented to be outgoing from the root.

An ancestor of a node is any node on the path from the root to that node (excluding the node itself). The root has no ancestors.



Example 1:


Input: pairs = [[1,2],[2,3]]
Output: 1
Explanation: There is exactly one valid rooted tree, which is shown in the above figure.
Example 2:


Input: pairs = [[1,2],[2,3],[1,3]]
Output: 2
Explanation: There are multiple valid rooted trees. Three of them are shown in the above figures.
Example 3:

Input: pairs = [[1,2],[2,3],[2,4],[1,5]]
Output: 0
Explanation: There are no valid rooted trees.


Constraints:

1 <= pairs.length <= 105
1 <= xi < yi <= 500
The elements in pairs are unique.

analysis:
the node with most degree is the root
take a top down approach by priorityQueue from the root node
the node's visited neighbor with fewest degree is its direct parent
 */
public class NumberOfWaysToReconstructATree {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] p : pairs) {
            graph.computeIfAbsent(p[0], x -> new HashSet<>()).add(p[1]);
            graph.computeIfAbsent(p[1], x -> new HashSet<>()).add(p[0]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Integer n : graph.keySet()) {
            pq.offer(new int[]{graph.get(n).size(), n});
        }
        int res = 1;
        int len = pq.size();
        Set<Integer> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            int cur = pq.poll()[1];
            visited.add(cur);
            int parent = -1;
            for (int nb : graph.get(cur)) {
                if (visited.contains(nb) &&
                        (parent == -1 || graph.get(nb).size() < graph.get(parent).size())) {
                    parent = nb;
                }
            }
            if (parent == -1) {
                if (graph.get(cur).size() != len - 1) {
                    return 0;
                }
                continue;
            }
            for (int nb : graph.get(cur)) {
                if (nb != parent && !graph.get(nb).contains(parent)) {
                    return 0;
                }
            }
            if (graph.get(parent).size() == graph.get(cur).size()) {
                res = 2;
            }
        }
        return res;
    }
}
