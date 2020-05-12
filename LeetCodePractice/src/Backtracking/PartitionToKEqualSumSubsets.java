package Backtracking;

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
	int target;
	boolean[] visited;

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		int len = nums.length;
		visited = new boolean[len];
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		if (k <= 0 || sum % k != 0) {
			return false;
		}
		target = sum / k;
		return dfs(nums, 0, 0, k);
	}

	private boolean dfs(int[] nums, int curIdx, int sum, int k) {
		if (k == 0) {
			return true;
		}
		if(sum > target){
			return false;
		}
		if (sum == target) {
			return dfs(nums, 0, 0, k - 1);
		}
		int len = nums.length;
		for (int i = curIdx; i < len; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (dfs(nums, i + 1, sum + nums[i], k)) {
					return true;
				}
				visited[i] = false;
			}
		}
		return false;
	}
}
