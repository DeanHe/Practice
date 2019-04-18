package DP;
/*We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20
Explanation: 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 

Note:

1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.*/
public class LargestSumOfAverages {
	//method1: top down, recursion with memorization
	public double largestSumOfAveragesTopDown(int[] A, int K) {
		int len = A.length;
		//largest sum of averages for first n elements in A parttioned into k group
		double[][] mem = new double[K + 1][len + 1];
		double[] sum = new double[len + 1];
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i - 1] + A[i - 1];
        }
        return dfs(A, sum, mem, K, len);
	}
	private double dfs(int[] A, double[] preSum, double[][] mem, int k, int n){
		if(mem[k][n] > 0){
			return mem[k][n];
		}
		// init: dp[1][i] = avg(A[0] ~ A[i - 1])
		if(k == 1){
			return preSum[n] / n;
		}
		for(int i = k - 1; i < n; i++){
			// transition: dp[k][i] = max(dp[k - 1][j] + avg(A[j + 1] ~ A[i]))
			mem[k][n] = Math.max(mem[k][n], dfs(A, preSum, mem, i, k - 1) + (preSum[n] - preSum[i]) / (n - i));
		}
		return mem[k][n];
	}
	//method2: 2D dp. Bottom up
	public double largestSumOfAveragesBottomUp(int[] A, int K) {
		if(A == null || A.length == 0){
            return 0;
        }
        int len = A.length;
		//largest sum of averages for first n elements in A parttioned into k group
		double[][] mem = new double[K + 1][len + 1];
		double[] sum = new double[len + 1];
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i - 1] + A[i - 1];
            mem[1][i] = sum[i] / i;
        }
        for(int k = 2; k <= K; k++){
        	for(int i = k; i <= len; i++){
        		for(int j = k - 1; j < i; j++){
        			mem[k][i] = Math.max(mem[k][i], mem[k - 1][j] + (sum[i] - sum[j]) / (i - j));
        		}
        	}
        }
        return mem[K][len];
	}
	//method3: 1D dp
	public double largestSumOfAverages(int[] A, int K) {
        if(A == null || A.length == 0){
            return 0;
        }
        int len = A.length;
        double[] sum = new double[len + 1];
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i - 1] + A[i - 1];
        }
        double[] dp = new double[len];
        for(int i = 0; i < len; i++){
            dp[i] = (sum[len] - sum[i]) / (len - i);
        }
        for(int k = 0; k < K - 1; k++){
            for(int i = 0; i < len; i++){
                for(int j = i + 1; j < len; j++){
                    dp[i] = Math.max(dp[i], dp[j] + (sum[j] - sum[i]) / (j -i));
                }
            }
        }
        return dp[0];
    }
}
