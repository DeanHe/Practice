package backtracking;

/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
can leads to infinite answers

What limitation we need to add to the question to allow negative numbers?
need to set limitation on length the nums array

analysis:
different order count as separate one

*/
public class CombinationSumIV {
	Integer[] mem;
	public int combinationSum4(int[] nums, int target) {
		mem = new Integer[target + 1];
		return dfs(nums, target);
	}

	private int dfs(int[] nums, int target) {
		if(target == 0){
			return 1;
		}
		if(mem[target] != null){
			return mem[target];
		}
		int res = 0;
		for(int n : nums){
			if(target >= n){
				res += dfs(nums, target - n);
			}
		}
		return mem[target] = res;
	}


	public int combinationSum4DP(int[] nums, int target) {
		int len = nums.length;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int j = 0; j < len; j++) {
				if (i >= nums[j]) {
					dp[i] += dp[i - nums[j]];
				}
			}
		}
		return dp[target];
	}


}
