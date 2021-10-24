package contest;

import java.util.*;

/*
There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.

Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.

Return the number of nodes that have the highest score.



Example 1:

example-1
Input: parents = [-1,2,0,2,0]
Output: 3
Explanation:
- The score of node 0 is: 3 * 1 = 3
- The score of node 1 is: 4 = 4
- The score of node 2 is: 1 * 1 * 2 = 2
- The score of node 3 is: 4 = 4
- The score of node 4 is: 4 = 4
The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
Example 2:

example-2
Input: parents = [-1,2,0]
Output: 2
Explanation:
- The score of node 0 is: 2 = 2
- The score of node 1 is: 2 = 2
- The score of node 2 is: 1 * 1 = 1
The highest score is 2, and two nodes (node 0 and node 1) have the highest score.


Constraints:

n == parents.length
2 <= n <= 10^5
parents[0] == -1
0 <= parents[i] <= n - 1 for i != 0
parents represents a valid binary tree.

hint:
1 For each node, you need to find the sizes of the subtrees rooted in each of its children. Maybe DFS?
2 How to determine the number of nodes in the rest of the tree? Can you subtract the size of the subtree rooted at the node from the total number of nodes of the tree?
3 Use these values to compute the score of the node. Track the maximum score, and how many nodes achieve such score.

analysis:
use a treeMap to store multiple result : cnt
use Long to handle overflow

TC O(N)
SC O(N)
 */
public class CountNodesWithTheHighestScore {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            g.computeIfAbsent(parents[i], x -> new ArrayList<>()).add(i);
        }
        TreeMap<Long, Integer> freqMap = new TreeMap<>();
        dfs(g, freqMap, 0, n);
        return freqMap.lastEntry().getValue();
    }

    private long dfs(Map<Integer, List<Integer>> g, TreeMap<Long, Integer> freqMap, int cur, int n) {
        long cnt = 0, multiple = 1;
        for (int child : g.getOrDefault(cur, new ArrayList<>())) {
            long t = dfs(g, freqMap, child, n);
            cnt += t;
            multiple *= t;
        }
        multiple *= Math.max(1, n - cnt - 1);
        freqMap.put(multiple, freqMap.getOrDefault(multiple, 0) + 1);
        return cnt + 1;
    }
}
