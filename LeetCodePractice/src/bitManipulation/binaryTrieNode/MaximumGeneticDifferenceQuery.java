package bitManipulation.binaryTrieNode;

import java.util.ArrayList;
import java.util.List;

/*
There is a rooted tree consisting of n nodes numbered 0 to n - 1. Each node's number denotes its unique genetic value (i.e. the genetic value of node x is x). The genetic difference between two genetic values is defined as the bitwise-XOR of their values. You are given the integer array parents, where parents[i] is the parent for node i. If node x is the root of the tree, then parents[x] == -1.

You are also given the array queries where queries[i] = [nodei, vali]. For each query i, find the maximum genetic difference between vali and pi, where pi is the genetic value of any node that is on the path between nodei and the root (including nodei and the root). More formally, you want to maximize vali XOR pi.

Return an array ans where ans[i] is the answer to the ith query.



Example 1:


Input: parents = [-1,0,1,1], queries = [[0,2],[3,2],[2,5]]
Output: [2,3,7]
Explanation: The queries are processed as follows:
- [0,2]: The node with the maximum genetic difference is 0, with a difference of 2 XOR 0 = 2.
- [3,2]: The node with the maximum genetic difference is 1, with a difference of 2 XOR 1 = 3.
- [2,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
Example 2:


Input: parents = [3,7,-1,2,0,7,0,2], queries = [[4,6],[1,15],[0,5]]
Output: [6,14,7]
Explanation: The queries are processed as follows:
- [4,6]: The node with the maximum genetic difference is 0, with a difference of 6 XOR 0 = 6.
- [1,15]: The node with the maximum genetic difference is 1, with a difference of 15 XOR 1 = 14.
- [0,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.


Constraints:

2 <= parents.length <= 10^5
0 <= parents[i] <= parents.length - 1 for every node i that is not the root.
parents[root] == -1
1 <= queries.length <= 3 * 10^4
0 <= nodei <= parents.length - 1
0 <= vali <= 2 * 10^5

hint:
How can we use a trie to store all the XOR values in the path from a node to the root?
How can we dynamically add the XOR values with a DFS search?

analysis:
Our Trie will store numbers in form of their 18 bit presentation, store the most significant bit near the root.
Why store 18 bits? Because 17th bit (zero-based indexing) is 2^17=131072 can present the maximum number which is 10^5.
 */
public class MaximumGeneticDifferenceQuery {
    BinaryTrieNode trieRoot;

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length, qlen = queries.length, root = -1;
        int[] res = new int[qlen];
        List<Integer>[] graph = new List[n];
        List<int[]>[] copyQueries = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            copyQueries[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                root = i;
            } else {
                graph[parents[i]].add(i);
            }
        }
        for (int i = 0; i < qlen; i++) {
            copyQueries[queries[i][0]].add(new int[]{queries[i][1], i});
        }
        trieRoot = new BinaryTrieNode();
        dfs(root, graph, copyQueries, res);
        return res;
    }

    private void dfs(int root, List<Integer>[] graph, List<int[]>[] copyQueries, int[] res) {
        trieRoot.increase(root, 1);
        for (int[] query : copyQueries[root]) {
            res[query[1]] = trieRoot.findMax(query[0]);
        }
        for (int child : graph[root]) {
            dfs(child, graph, copyQueries, res);
        }
        trieRoot.increase(root, -1);
    }

    private class BinaryTrieNode {
        int children = 0;
        BinaryTrieNode[] arr;

        public BinaryTrieNode() {
            arr = new BinaryTrieNode[2];
        }

        public void increase(int num, int d) {
            BinaryTrieNode cur = this;
            for (int i = 17; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.arr[bit] == null) {
                    cur.arr[bit] = new BinaryTrieNode();
                }
                cur = cur.arr[bit];
                cur.children += d;
            }
        }

        public int findMax(int num) {
            int res = 0;
            BinaryTrieNode cur = this;
            for (int i = 17; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (cur.arr[1 - bit] != null && cur.arr[1 - bit].children > 0) {
                    cur = cur.arr[1 - bit];
                    res |= (1 << i);
                } else {
                    cur = cur.arr[bit];
                }
            }
            return res;
        }
    }
}

