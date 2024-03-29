package MinMax;

/*
Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on.
Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.

Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.

Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

analysis:
https://leetcode.com/problems/predict-the-winner/discuss/96828/JAVA-9-lines-DP-solution-easy-to-understand-with-improvement-to-O(N)-space-complexity.
*/
public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        //dp[i][j] saves how much more scores that the first-in-action player will get from i to j than the second player
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int l = 1; l < len; l++) {
            for (int i = 0; i + l < len; i++) {
                int j = i + l;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }

    public boolean PredictTheWinner1D(int[] nums) {
        int len = nums.length;
        //dp[i] saves how much more scores that the first-in-action player will get from 0 to i than the second player
        int[] dp = new int[len];
        for (int r = len - 1; r >= 0; r--) {
            for (int c = r; c < len; c++) {
                if (r == c) {
                    dp[c] = nums[r];
                } else {
                    dp[c] = Math.max(nums[r] - dp[c], nums[c] - dp[c - 1]);
                }
            }
        }
        return dp[len - 1] >= 0;
    }

    public boolean PredictTheWinnerRecursion(int[] nums) {
        int len = nums.length;
        Integer[][]  mem = new Integer[len][len];
        return dfs(nums, mem, 0, nums.length - 1) >= 0;
    }

    private int dfs(int[] nums, Integer[][] mem, int s, int e) {
        if (s == e) {
            return nums[s];
        }
        if(mem[s][e] != null){
            return mem[s][e];
        }
        return mem[s][e] = Math.max(nums[s] - dfs(nums, mem,s + 1, e), nums[e] - dfs(nums, mem, s, e - 1));
    }
}
