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
        for (int i = 0; i < k; i++) {
            res.add(new ArrayList<>());
        }
        dfs(nums, new int[k], res, nums.length - 1, sum / k);
        return res;
    }

    private boolean dfs(int[] nums, int[] sums, List<List<Integer>> res, int pos, int target) {
        if (pos == -1) {
            for (int sum : sums) {
                if (sum != target) {
                    return false;
                }
            }
            return true;
        }
        for (int sum : sums) {
            if (sum > target) {
                return false;
            }
        }
        for (int i = 0; i < sums.length; i++) {
            sums[i] += nums[pos];
            res.get(i).add(nums[pos]);
            if (dfs(nums, sums, res, pos - 1, target)) {
                return true;
            }
            sums[i] -= nums[pos];
            int last = res.get(i).size() - 1;
            res.get(i).remove(last);
        }
        return false;
    }
}
