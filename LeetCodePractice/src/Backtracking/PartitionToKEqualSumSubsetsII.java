package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/
public class PartitionToKEqualSumSubsetsII {
	int len, target, k;
	boolean[] visited;
	int[]nums, subsetSum;
	List<List<Integer>> res;

	public List<List<Integer>> canPartitionKSubsets(int[] nums, int k) {
		this.nums = nums;
		int sum = 0;
		len = nums.length;
		visited = new boolean[len];
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		res = new ArrayList<>();
		if (k <= 0 || sum % k != 0) {
			return res;
		}
		for (int i = 0; i < k; i++) {
			res.add(new ArrayList<>());
		}
		subsetSum = new int[k];
		target = sum / k;
		this.k = k;
		dfs(0);
		return res;
	}

	private boolean dfs(int idx) {
		boolean partEqual = true;
		for (int i = 0; i < k; i++) {
			partEqual = partEqual && (subsetSum[i] == target);
			if (subsetSum[i] > target) {
				return false;
			}
		}
		if (partEqual) {
			return true;
		}
		for (int i = idx; i < len; i++) {
			for (int j = 0; j < k; j++) {
				List<Integer> subset = res.get(j);
				if (!visited[i]) {
					visited[i] = true;
					subset.add(nums[i]);
					subsetSum[j] += nums[i];
					if (dfs(i + 1)) {
						return true;
					}
					subset.remove(subset.size() - 1);
					subsetSum[j] -= nums[i];
					visited[i] = false;
				}
			}
		}
		return false;
	}
}
