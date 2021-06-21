package graph.hungarian;
/*
There are m boys and n girls in a class attending an upcoming party.
You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.
Return the maximum possible number of accepted invitations.
Example 1:
Input: grid = [[1,1,1],
               [1,0,1],
               [0,0,1]]
Output: 3
Explanation: The invitations are sent as follows:
- The 1st boy invites the 2nd girl.
- The 2nd boy invites the 1st girl.
- The 3rd boy invites the 3rd girl.
Example 2:
Input: grid = [[1,0,1,0],
               [1,0,0,0],
               [0,0,1,0],
               [1,1,1,0]]
Output: 3
Explanation: The invitations are sent as follows:
-The 1st boy invites the 3rd girl.
-The 2nd boy invites the 1st girl.
-The 3rd boy invites no one.
-The 4th boy invites the 2nd girl.
Constraints:
grid.length == m
grid[i].length == n
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
 */
public class MaximumNumberOfAcceptedInvitations {
    public int maximumInvitations(boolean[][] grid) {
        int match = 0;
        int[] girls = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            boolean[] visited = new boolean[grid.length];
            if (find(i, grid, girls, visited)) {
                match++;
            }
        }
        return match;
    }

    // match[j], for each record j in B set, it matches to a certain record in A set.
    boolean find(int i, boolean[][] graph, int[] pair, boolean[] visited) {
        for (int j = 0; j < graph[0].length; j++) {
            if (!graph[i][j] || visited[j]) {
                continue;
            }
            visited[j] = true;
            if (pair[j] == -1 || find(pair[j], graph, pair, visited)) {
                pair[j] = i;
                return true;
            }
        }
        return false;
    }
}
