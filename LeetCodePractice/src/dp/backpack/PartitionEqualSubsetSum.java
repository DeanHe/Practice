package dp.backpack;

/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

        Note:

        Each of the array element will not exceed 100.
        The array size will not exceed 200.


        Example 1:

        Input: [1, 5, 11, 5]

        Output: true

        Explanation: The array can be partitioned as [1, 5, 5] and [11].


        Example 2:

        Input: [1, 2, 3, 5]

        Output: false

        Explanation: The array cannot be partitioned into equal sum subsets.

        analysis:
        Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not.
        Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
        If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
        TC O(N * M)

        similar to:
        Partition to K Equal Sum Subsets
*/

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int len = nums.length, sum = 0, target = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        target = sum / 2;
        //d[i][j] means can select subset from nums[:i] to sum j
        boolean[][] dp = new boolean[len + 1][target + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int s = 1; s <= target; s++) {
                dp[i][s] = dp[i - 1][s];
                if (s >= nums[i - 1]) {
                    dp[i][s] |= dp[i - 1][s - nums[i - 1]];
                }
            }
        }
        return dp[len][target];
    }

    //dfs slow
    public boolean canPartitionII(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 == 1) {
            return false;
        }
        boolean[] visited = new boolean[len];
        return dfs(nums, visited, 2, nums.length - 1, 0, sum / 2);
    }

    private boolean dfs(int[] nums, boolean[] visited, int k, int pos, int sum, int target) {
        if (sum > target) {
            return false;
        }
        if (k == 0) {
            return true;
        }
        if (sum == target) {
            return dfs(nums, visited, k - 1, nums.length - 1, 0, target);
        }
        for (int i = pos; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                if (dfs(nums, visited, k, i - 1, sum + nums[i], target)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
