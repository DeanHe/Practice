package DP;

/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

        The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

        Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

        In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



        Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

        For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

        -2 (K)	-3	3
        -5	-10	1
        10	30	-5 (P)


        Note:

        The knight's health has no upper bound.
        Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        RuntimeException e = new RuntimeException("");
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
            return 0;
        }
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        int[][] dp = new int[rows][cols];
        dp[rows - 1][cols - 1] = Math.max(1, 1 - dungeon[rows - 1][cols - 1]);
        for(int r = rows - 2; r >= 0; r--){
            dp[r][cols - 1] = Math.max(1, dp[r + 1][cols - 1] - dungeon[r][cols - 1]);
        }
        for(int c = cols - 2; c >= 0; c--){
            dp[rows - 1][c] = Math.max(1, dp[rows - 1][c + 1] - dungeon[rows - 1][c]);
        }
        for(int r = rows - 2; r >= 0; r--){
            for(int c = cols - 2; c >= 0; c--){
                dp[r][c] = Math.max(1, Math.min(dp[r + 1][c], dp[r][c + 1]) - dungeon[r][c]);
            }
        }
        return dp[0][0];
    }
}
