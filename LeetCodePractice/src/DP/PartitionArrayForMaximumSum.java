package DP;

/*Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning.

Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]*/
public class PartitionArrayForMaximumSum {
	public int maxSumAfterPartitioning(int[] A, int K) {
		int len = A.length;
		int[] dp = new int[len + 1]; // dp[i] means max sum of A[:i - 1]
		for (int i = 1; i <= len; i++) {
			int subMax = Integer.MIN_VALUE;
			for (int l = 1; l <= Math.min(i, K); l++) {
				subMax = Math.max(subMax, A[i - l]);
				dp[i] = Math.max(dp[i], dp[i - l] + l * subMax);
			}
		}
		return dp[len];
	}
}
