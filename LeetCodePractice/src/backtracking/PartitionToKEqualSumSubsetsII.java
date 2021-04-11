package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
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

analysis:
TC O(k * 2^N)
*/
public class PartitionToKEqualSumSubsetsII {
    public List<List<Integer>> canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || sum % k != 0) {
            return res;
        }
        for(int i = 0; i < k; i++){
            res.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, res, nums.length - 1, 0, sum / k, k);
        return res;
    }

    private boolean dfs(int[] nums, boolean[] visited, List<List<Integer>> res, int pos, int sum, int target, int k) {
        if (sum > target) {
            return false;
        }
        if (k == 0) {
            return true;
        }
        if (sum == target) {
            return dfs(nums, visited, res, nums.length - 1, 0, target, k - 1);
        }
        for (int i = pos; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                List<Integer> ls = res.get(k - 1);
                ls.add(nums[i]);
                if (dfs(nums, visited, res, i - 1, sum + nums[i], target, k)) {
                    return true;
                }
                ls.remove(ls.size() - 1);
                visited[i] = false;
            }
        }
        return false;
    }
}
