package dp;
/*
Given n distinct positive integers, integer k (k <= n) and a number target.
Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.
There are 2 solutions: [1,4] and [2,3].
Return 2.
*/
public class Ksum {
	/**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        // write your code here
        int len = A.length;
        int[][][] dp = new int[len + 1][k + 1][target + 1];
        //mem[i][j][m] means select j elements from A[:i] to sum up to m
        //init
        for(int i = 0; i <= len; i++){
            dp[i][0][0] = 1;
        }
        //function
        for(int i = 1; i <= len; i++){
            // can't take more than available
            for(int j = 1; j <= k && j <= i; j++){
                for(int t = 1; t <= target; t++){
                    dp[i][j][t] = dp[i - 1][j][t];
                    //take A[i]
                    if(t >= A[i - 1]){
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                    //dont take A[i]
                }
            }
        }
        
        return dp[len][k][target];
    }
}
