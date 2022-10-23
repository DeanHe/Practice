package dp;

/*
We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

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
1 <= K <= nums.length.
Answers within 10^-6 of the correct answer will be accepted as correct.

analysis:
TC O(K * N^2)
SC O(N^2)
*/
public class LargestSumOfAverages {
    //method1: top down, recursion with memorization
    public double largestSumOfAveragesTopDown(int[] nums, int K) {
        int len = nums.length;
        //largest sum of averages for first n elements in nums partitioned into k group
        double[][] mem = new double[K + 1][len + 1];
        double[] preSum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return dfs(nums, preSum, mem, K, len);
    }

    private double dfs(int[] nums, double[] preSum, double[][] mem, int k, int n) {
        if (mem[k][n] > 0) {
            return mem[k][n];
        }
        // init: mem[1][i] = avg(nums[0] ~ nums[i - 1])
        if (k == 1) {
            return preSum[n] / n;
        }
        for (int i = k - 1; i < n; i++) {
            // transition: mem[k][i] = max(mem[k - 1][j] + avg(nums[j + 1] ~ nums[i]))
            mem[k][n] = Math.max(mem[k][n], dfs(nums, preSum, mem, k - 1, i) + (preSum[n] - preSum[i]) / (n - i));
        }
        return mem[k][n];
    }

    //method2: 2D mem. Bottom up
    public double largestSumOfAveragesBottomUp(int[] nums, int K) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        //largest sum of averages for first n elements in nums partitioned into k group
        double[][] dp = new double[K + 1][len + 1];
        double[] preSum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            dp[1][i + 1] = preSum[i + 1] / (i + 1);
        }
        for (int k = 2; k <= K; k++) {
            for (int i = k; i <= len; i++) {
                for (int j = k - 1; j < i; j++) {
                    dp[k][i] = Math.max(dp[k][i], dp[k - 1][j] + (preSum[i] - preSum[j]) / (i - j));
                }
            }
        }
        return dp[K][len];
    }

    //method3: 1D mem
    public double largestSumOfAverages(int[] nums, int K) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        double[] preSum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        double[] dp = new double[len];
        for (int i = 0; i < len; i++) {
            dp[i] = (preSum[len] - preSum[i]) / (len - i);
        }
        for (int k = 0; k < K - 1; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    dp[i] = Math.max(dp[i], dp[j] + (preSum[j] - preSum[i]) / (j - i));
                }
            }
        }
        return dp[0];
    }
}
