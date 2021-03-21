package backtracking;

import java.util.Arrays;

/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/
public class PartitionToKEqualSumSubsets {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		int len = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		if (k <= 0 || sum % k != 0) {
			return false;
		}
		int target = sum / k;
		return dfs(nums, new int[k],len - 1, target);
	}

	private boolean dfs(int[] nums, int[] sums, int pos, int target) {
		if (pos == -1) {
			for(int sum : sums){
				if(sum != target){
					return false;
				}
			}
			return true;
		}
		for(int sum : sums){
			if(sum > target){
				return false;
			}
		}
		for (int i = 0; i < sums.length; i++) {
			if(i > 0 && sums[i] == sums[i - 1]){
				continue;
			}
			sums[i] += nums[pos];
			if(dfs(nums, sums, pos - 1, target)){
				return true;
			}
			sums[i] -= nums[pos];
		}
		return false;
	}
}
