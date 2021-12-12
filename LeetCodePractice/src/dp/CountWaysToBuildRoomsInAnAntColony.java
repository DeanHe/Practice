package dp;

import java.util.ArrayList;

/*
You are an ant tasked with adding n new rooms numbered 0 to n-1 to your colony. You are given the expansion plan as a 0-indexed integer array of length n, prevRoom, where prevRoom[i] indicates that you must build room prevRoom[i] before building room i, and these two rooms must be connected directly. Room 0 is already built, so prevRoom[0] = -1. The expansion plan is given such that once all the rooms are built, every room will be reachable from room 0.

You can only build one room at a time, and you can travel freely between rooms you have already built only if they are connected. You can choose to build any room as long as its previous room is already built.

Return the number of different orders you can build all the rooms in. Since the answer may be large, return it modulo 10^9 + 7.



Example 1:


Input: prevRoom = [-1,0,1]
Output: 1
Explanation: There is only one way to build the additional rooms: 0 → 1 → 2

Example 2:
Input: prevRoom = [-1,0,0,1,2]
Output: 6
Explanation:
The 6 ways are:
0 → 1 → 3 → 2 → 4
0 → 2 → 4 → 1 → 3
0 → 1 → 2 → 3 → 4
0 → 1 → 2 → 4 → 3
0 → 2 → 1 → 3 → 4
0 → 2 → 1 → 4 → 3


Constraints:

n == prevRoom.length
2 <= n <= 10^5
prevRoom[0] == -1
0 <= prevRoom[i] < n for all 1 <= i < n
Every room is reachable from room 0 once all the rooms are built.

hint:
1 Use dynamic programming.
2 Let dp[i] be the number of ways to solve the problem for the subtree of node i.
3 Imagine you are trying to fill an array with the order of traversal, dp[i] equals the multiplications of the number of ways to distribute the subtrees of the children of i on the array using combinatorics, multiplied by their dp values.

analysis:
total ways are: nodes! / multi(treeSize[:])
https://leetcode.com/problems/count-ways-to-build-rooms-in-an-ant-colony/discuss/1299661/DFS-oror-Combinatorics-oror-Modulo-Inverse-oror-DP-oror-Solution-Explained-oror-Easy-Code-oror-Hard-Logic
 */
public class CountWaysToBuildRoomsInAnAntColony {
    int MOD = (int) (1e9 + 7);

    public int waysToBuildRooms(int[] prevRoom) {
        int len = prevRoom.length;
        ArrayList<Integer>[] tree = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < len; i++) {
            tree[prevRoom[i]].add(i);
        }
        // To store the subtree size for each node.
        int[] treeSize = new int[len];
        dfs(tree, treeSize, 0);
        // Product of all the sizes of subtrees.
        long product = 1;
        for (int i = 0; i < len; i++) {
            product = (product * treeSize[i]) % MOD;
        }
        int p = (int) (product);
        // Find n factorial
        long factorial = 1;
        for (int i = 2; i <= len; i++) {
            factorial = (factorial * i) % MOD;
        }
        // To divide two number using modulo we find modulo inverse of denominator with mod and then multiply it with the numerator.
        int inverse = modInverse(p);
        return (int) ((factorial * inverse) % MOD);
    }

    private int dfs(ArrayList<Integer>[] tree, int[] treeSize, int root) {
        if (treeSize[root] != 0) {
            return treeSize[root];
        }
        int res = 1;
        for (int child : tree[root]) {
            res += dfs(tree, treeSize, child);
        }
        treeSize[root] = res;
        return res;
    }

    private int modInverse(int n) {
        return power(n, MOD - 2, MOD);
    }

    private int power(int x, int y, int m) {
        if (y == 0)
            return 1;
        int p = power(x, y / 2, m) % m;
        p = (int) ((p * (long) p) % m);
        if (y % 2 == 0)
            return p;
        else
            return (int) ((x * (long) p) % m);
    }
}
