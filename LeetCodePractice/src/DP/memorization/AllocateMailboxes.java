package DP.memorization;

/*
Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.

        Return the minimum total distance between each house and its nearest mailbox.

        The answer is guaranteed to fit in a 32-bit signed integer.



        Example 1:



        Input: houses = [1,4,8,10,20], k = 3
        Output: 5
        Explanation: Allocate mailboxes in position 3, 9 and 20.
        Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
        Example 2:



        Input: houses = [2,3,5,12,18], k = 2
        Output: 9
        Explanation: Allocate mailboxes in position 3 and 14.
        Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
        Example 3:

        Input: houses = [7,4,6,1], k = 1
        Output: 8
        Example 4:

        Input: houses = [3,6,14,10], k = 4
        Output: 0


        Constraints:

        n == houses.length
        1 <= n <= 100
        1 <= houses[i] <= 10^4
        1 <= k <= n
        array houses contain unique integers.
*/

import java.util.Arrays;

public class AllocateMailboxes {
    int UPPER_BOUND = 10000000;
    Integer[][] dp;
    int[][] cost;
    int len, k;
    public int minDistance(int[] houses, int k) {
        this.k = k;
        this.len = houses.length;
        Arrays.sort(houses);
        cost = new int[len][len];
        dp = new Integer[len][k];
        for(int s = 0; s < len; s++){
            for(int l = 0; s + l < len; l++){
                int e = s + l;
                int median = houses[(s + e) / 2];
                for(int i = s; i <= e; i++){
                    cost[s][e] += Math.abs(houses[i] - median);
                }
            }
        }
        return dfs(houses, 0, 0);
    }

    private int dfs(int[] houses, int cur, int group) {
        if(cur == len && group == k){
            return 0;
        }
        if(cur == len || group == k){
            return UPPER_BOUND;
        }
        if(dp[cur][group] != null){
            return dp[cur][group];
        }
        int res = UPPER_BOUND;
        for(int i = cur; i < len; i++){
            res = Math.min(res, cost[cur][i] + dfs(houses, i + 1, group + 1));
        }
        return dp[cur][group] = res;
    }
}
