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

TC O(k * 2^N)
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
		boolean[] visited = new boolean[len];
		return dfs(nums, visited, k, len - 1, 0, target);
	}

	private boolean dfs(int[] nums, boolean[] visited, int k, int pos, int sum, int target) {
		if(sum > target){
			return false;
		}
		if(k == 0){
			return true;
		}
		if (sum == target) {
			return dfs(nums, visited, k - 1, nums.length - 1, 0, target);
		}
		for(int i = pos; i >= 0; i--){
			if(!visited[i]){
				visited[i] = true;
				if(dfs(nums, visited, k, i - 1, sum + nums[i], target)){
					return true;
				}
				visited[i] = false;
			}
		}
		return false;
	}
}
