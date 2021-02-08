package Contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
#1755

You are given an integer array nums and an integer goal.

You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).

Return the minimum possible value of abs(sum - goal).

Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.



Example 1:

Input: nums = [5,-7,3,5], goal = 6
Output: 0
Explanation: Choose the whole array as a subsequence, with a sum of 6.
This is equal to the goal, so the absolute difference is 0.
Example 2:

Input: nums = [7,-9,15,-2], goal = -5
Output: 1
Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
Example 3:

Input: nums = [1,2,3], goal = -7
Output: 7


Constraints:

1 <= nums.length <= 40
-10^7 <= nums[i] <= 10^7
-10^9 <= goal <= 10^9

analysis:
divide and conquer; meet in the middle
TC: O(N * 2 ^(N / 2))
 */
public class ClosestSubsequenceSum {
    public int minAbsDifference(int[] nums, int goal) {
        int len = nums.length;
        int res = Math.abs(goal);
        List<Integer> leftSums = new ArrayList<>();
        List<Integer> rightSums = new ArrayList<>();
        dfs(nums, 0, len / 2, 0, leftSums);
        dfs(nums, len / 2 + 1, len - 1, 0, rightSums);
        Collections.sort(leftSums);
        for (int r : rightSums) {
            int remain = goal - r; // How far off are we from the desired goal?
            if (leftSums.get(0) > remain) { // all subset sums from first half are too big => Choose the smallest
                res = Math.min(res, Math.abs(leftSums.get(0) + r - goal));
                continue;
            }
            if (leftSums.get(leftSums.size() - 1) < remain) { // all subset sums from first half are too small => Choose the largest
                res = Math.min(res, Math.abs(leftSums.get(leftSums.size() - 1) + r - goal));
                continue;
            }
            int idx = Collections.binarySearch(leftSums, remain);
            if (idx >= 0) { // Exact match found! => first.get(idx) + r == goal
                return 0;
            } else {
                idx = -1 * (idx + 1);
                res = Math.min(res, Math.abs(leftSums.get(idx) + r - goal));
                res = Math.min(res, Math.abs(leftSums.get(idx - 1) + r - goal));
            }
        }
        return res;
    }
    // get all subsequence sum from start to end
    private void dfs(int[] nums, int start, int end, int sum, List<Integer> sums) {
        if (start > end) {
            sums.add(sum);
            return;
        }
        dfs(nums, start + 1, end, sum, sums);
        dfs(nums, start + 1, end, sum + nums[start], sums);
    }
}
