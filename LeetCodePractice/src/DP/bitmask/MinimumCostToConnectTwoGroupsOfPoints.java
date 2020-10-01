package DP.bitmask;

import java.util.Arrays;
import java.util.List;

/*
You are given two groups of points where the first group has size1 points, the second group has size2 points, and size1 >= size2.

The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j] is the cost of connecting point i of the first group and point j of the second group. The groups are connected if each point in both groups is connected to one or more points in the opposite group. In other words, each point in the first group must be connected to at least one point in the second group, and each point in the second group must be connected to at least one point in the first group.

Return the minimum cost it takes to connect the two groups.



Example 1:


Input: cost = [[15, 96], [36, 2]]
Output: 17
Explanation: The optimal way of connecting the groups is:
1--A
2--B
This results in a total cost of 17.
Example 2:


Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
Output: 4
Explanation: The optimal way of connecting the groups is:
1--A
2--B
2--C
3--A
This results in a total cost of 4.
Note that there are multiple points connected to point 2 in the first group and point A in the second group. This does not matter as there is no limit to the number of points that can be connected. We only care about the minimum total cost.
Example 3:

Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
Output: 10


Constraints:

size1 == cost.length
size2 == cost[i].length
1 <= size1, size2 <= 12
size1 >= size2
0 <= cost[i][j] <= 100

analysis:
use mask to track which element is group2 (or b) is connected
1 calculate min cost from group2 to group1 for each node in group2
2 dfs + mem on each possible group1 node connection to group2 node
3 for the remaining unconnected nodes in group2, connect back to group1 with their minimal costs
 */
public class MinimumCostToConnectTwoGroupsOfPoints {
    Integer[][] mem;
    int aLen, bLen;

    public int connectTwoGroups(List<List<Integer>> cost) {
        mem = new Integer[13][1 << 12];
        aLen = cost.size();
        bLen = cost.get(0).size();
        int[] minCostFromBtoA = new int[bLen];
        Arrays.fill(minCostFromBtoA, Integer.MAX_VALUE);
        int res = 0;
        for (int b = 0; b < bLen; b++) {
            for (int a = 0; a < aLen; a++) {
                minCostFromBtoA[b] = Math.min(minCostFromBtoA[b], cost.get(a).get(b));
            }
        }
        return dfs(cost, minCostFromBtoA, 0, 0);
    }

    private int dfs(List<List<Integer>> cost, int[] minCostFromBtoA, int a, int mask) {
        if (mem[a][mask] != null) {
            return mem[a][mask];
        }
        if (a == aLen) {
            int res = 0;
            for (int b = 0; b < bLen; b++) {
                if ((mask & (1 << b)) == 0) {
                    res += minCostFromBtoA[b];
                }
            }
            return mem[a][mask] = res;
        }
        int res = Integer.MAX_VALUE;
        for (int b = 0; b < bLen; b++) {
            res = Math.min(res, cost.get(a).get(b) + dfs(cost, minCostFromBtoA, a + 1, mask | (1 << b)));
        }
        return mem[a][mask] = res;
    }
}
