package Backtracking;
/*Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.*/
public class PartitionToKEqualSumSubsets {
	public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int len = nums.length;
        boolean[] visited = new boolean[len];
        for(int i = 0; i < len; i++){
        	sum += nums[i];
        }
        if(k <= 0 || sum % k != 0){
        	return false;
        }
        return dfs(nums, visited, 0, 0, sum / k, k);
    }
	private boolean dfs(int[] nums, boolean[] visited, int curIdx, int sum, int target, int k) {
		if(k == 0){
			return true;
		}
		if(sum == target){
			return dfs(nums, visited, 0, 0, target, k - 1);
		}
		int len = nums.length;
		for(int i = curIdx; i < len; i++){
			if(!visited[i]){
				visited[i] = true;
				if(dfs(nums, visited, i + 1, sum + nums[i], target, k)){
					return true;
				}
				visited[i] = false;
				
			}
		}
		return false;
	}
}
