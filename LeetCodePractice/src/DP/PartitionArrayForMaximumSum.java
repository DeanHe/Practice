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
		int[] dp = new int[len];
		for (int i = 0; i < len; i++) {
			int subMax = Integer.MIN_VALUE;
			for (int k = 1; k <= K && i - k + 1 >= 0; k++) {
				subMax = Math.max(subMax, A[i - k + 1]);
				if (i - k >= 0) {
					dp[i] = Math.max(dp[i], dp[i - k] + k * subMax);
				} else {
					dp[i] = Math.max(dp[i], k * subMax);
				}
			}
		}
		return dp[len - 1];
	}
}
