package graph.hungarian;
/*
There are m boys and n girls in a class attending an upcoming party.
You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1.
If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party.
A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.
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

analysis:
two path: pair or not left with no pair
 */
public class MaximumNumberOfAcceptedInvitations {
    int most;
    public int maximumInvitations(boolean[][] grid) {
        most = 0;
        boolean[] visited = new boolean[grid.length];
        dfs(grid, visited,0, 0);
        return most;
    }

    // match[j], for each record j in B set, it matches to a certain record in A set.
    private void dfs(boolean[][] graph, boolean[] visited, int pos, int score) {
        int res = 0;
        if(pos == graph.length){
            most = Math.max(most, score);
            return;
        }
        dfs(graph, visited, pos + 1, score);
        for (int j = 0; j < graph.length; j++) {
            if (!graph[pos][j] || visited[j]) {
                continue;
            }
            visited[j] = true;
            dfs(graph, visited, pos + 1, score + 1);
            visited[j] = false;
        }
    }
}
