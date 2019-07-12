package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.*/
public class PartitionToKEqualSumSubsetsII {
	int len, target, k;

	public List<List<Integer>> canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		len = nums.length;
		boolean[] visited = new boolean[len];
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		List<List<Integer>> res = new ArrayList<>();
		if (k <= 0 || sum % k != 0) {
			return res;
		}
		for (int i = 0; i < k; i++) {
			res.add(new ArrayList<Integer>());
		}
		int[] tempSum = new int[len];
		target = sum / k;
		this.k = k;
		dfs(res, nums, visited, 0, tempSum);
		return res;
	}

	private boolean dfs(List<List<Integer>> res, int[] nums, boolean[] visited, int curIdx, int[] tempSum) {
		boolean found = true;
		for (int i = 0; i < k; i++) {
			found = found && (tempSum[i] == target);
			if (tempSum[i] > target) {
				return false;
			}
		}
		if (found) {
			return true;
		}
		for (int i = curIdx; i < len; i++) {
			for (int j = 0; j < k; j++) {
				List<Integer> temp = res.get(j);
				if (!visited[i]) {
					visited[i] = true;
					temp.add(nums[i]);
					tempSum[j] += nums[i];
					if (dfs(res, nums, visited, i + 1, tempSum)) {
						return true;
					}
					temp.remove(temp.size() - 1);
					tempSum[j] -= nums[i];
					visited[i] = false;
				}
			}
		}
		return false;
	}
}
