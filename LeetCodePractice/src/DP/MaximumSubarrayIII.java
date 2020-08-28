package DP;

import java.util.*;

/*
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Example
Example 1

Input: 
List = [1,2,3]
k = 1
Output: 6
Explanation: 1 + 2 + 3 = 6
Example 2

Input:
List = [-1,4,-2,3,-2,3]
k = 2
Output: 8
Explanation: 4 + (3 + -2 + 3) = 8
Notice
The subarray should contain at least one number*/
public class MaximumSubarrayIII {
	/**
	 * @param nums:
	 *            A list of integers
	 * @param k:
	 *            An integer denote to find k non-overlapping subarrays
	 * @return: An integer denote the sum of max k non-overlapping subarrays
	 */
	public int maxSubArray(int[] nums, int k) {
		// write your code
		int len = nums.length;
		if (k > len) {
			return 0;
		}
		int[][] localMax = new int[k + 1][len + 1];
		// localMax[i][j] means max sum of having i subarrays with last array
		// ended with nums[j];
		int[][] gblMax = new int[k + 1][len + 1];
		// gblMax[i][j] means max sum of having i subarrays from nums[0:j];
		for (int i = 1; i <= k; i++) {
			localMax[i][i - 1] = Integer.MIN_VALUE;
			gblMax[i][i - 1] = Integer.MIN_VALUE;
			for (int j = i; j <= len; j++) {
				localMax[i][j] = Math.max(localMax[i][j - 1], gblMax[i - 1][j - 1]) + nums[j - 1];
				gblMax[i][j] = Math.max(gblMax[i][j - 1], localMax[i][j]);
			}
		}
		return gblMax[k][len];
	}
}
