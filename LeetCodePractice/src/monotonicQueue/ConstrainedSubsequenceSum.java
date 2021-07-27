package monotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/*
        Given an integer array nums and an integer k, return the maximum sum of a non-empty subset of that array such that for every two consecutive integers in the subset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
        A subset of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

        Example 1:

        Input: nums = [10,2,-10,5,20], k = 2
        Output: 37
        Explanation: The subset is [10, 2, 5, 20].
        Example 2:

        Input: nums = [-1,-2,-3], k = 1
        Output: -1
        Explanation: The subset must be non-empty, so we choose the largest number.
        Example 3:

        Input: nums = [10,-2,-10,-5,20], k = 2
        Output: 23
        Explanation: The subset is [10, -2, -5, 20].


        Constraints:

        1 <= k <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4

        solutions
        We need to know the maximum in the window of size k.
        Use deque will be O(N)

        1 maintain deque in correct window size
        2 get the valid max previous sum
        3 maintain deque in descending order
        4 push current idx to deque
*/
public class ConstrainedSubsequenceSum {
    public int constrainedSubsetSum(int[] nums, int k) {
        int res = Integer.MIN_VALUE, len = nums.length;
        //monotonic decreasing of dp value
        Deque<Integer> deque = new ArrayDeque<>();
        //dp[i] means the the max SubSet Sum with end in nums[i]
        int[] dp = new int[len];
        //dp[i] = nums[i] + Max(dp[i - k]...dp[i - 1])
        for (int i = 0; i < len; i++) {
            dp[i] = nums[i];
            while (!deque.isEmpty() && i - deque.peekFirst() > k) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                dp[i] += dp[deque.peekFirst()];
            }
            while (!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
                deque.pollLast();
            }
            if(dp[i] > 0){
                deque.offerLast(i);
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
