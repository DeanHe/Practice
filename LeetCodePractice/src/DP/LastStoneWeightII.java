package DP;
/*We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

 

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 

Note:

1 <= stones.length <= 30
1 <= stones[i] <= 100

This question eaquals to partition an array into 2 subsets whose difference is minimal
(1) S_big + S_small  = total
(2) S_big - S_small = diff  

==> -> diff = total - 2 * S_small  ==> minimize diff equals to  maximize S_small 

Now we should find the maximum of S_small, range from 0 to total / 2, using dp can solve this

dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
    i ranges from (sum of all elements) {1..n}
    j ranges from  {1..n}

same as 494. Target Sum 0~1 backpack
*/

public class LastStoneWeightII {
	public int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        int total = 0, maxOfSmall = 0;
        for(int stone : stones){
        	total += stone;
        }
        boolean[][] dp = new boolean[total + 1][len + 1];
        for(int i = 0; i <= len; i++){
        	dp[0][i] = true;
        }
        for(int s_small = 0; s_small <= total / 2; s_small++){
        	for(int i = 1; i <= len; i++){
        		if(dp[s_small][i - 1] || (s_small >= stones[i - 1] && dp[s_small - stones[i - 1]][i - 1])){
        			dp[s_small][i] = true;
        			maxOfSmall = Math.max(maxOfSmall, s_small);
        		}
        	}
        }
        return total - 2 * maxOfSmall;
    }
}
