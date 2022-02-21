package monotonicQueue;
/*
Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.

In case there is no subarray satisfying the given condition return 0.

Example 1:
Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.

Example 2:
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3


Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9

hint:
Use a sliding window approach keeping the maximum and minimum value using a data structure like a multiset from STL in C++.

More specifically, use the two pointer technique, moving the right pointer as far as possible to the right until the subarray is not valid (maxValue - minValue > limit),
then moving the left pointer until the subarray is valid again (maxValue - minValue <= limit). Keep repeating this process.

similar:
ConstrainedSubsequenceSum

analysis:
maintain monotonic increasing and decreasing queue for easily find max element and min element between window [l, r]
need to satisfy max - min <= limit for the window [l, r]
*/

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public int longestSubarray(int[] nums, int limit) {
        int res = 0, l = 0, len = nums.length;
        Deque<Integer> maxDeq = new ArrayDeque<>();
        Deque<Integer> minDeq = new ArrayDeque<>();
        for (int r = 0; r < len; r++) {
            while (!maxDeq.isEmpty() && nums[r] > nums[maxDeq.peekLast()]) {
                maxDeq.pollLast();
            }
            maxDeq.offerLast(r);
            while (!minDeq.isEmpty() && nums[r] < nums[minDeq.peekLast()]) {
                minDeq.pollLast();
            }
            minDeq.offerLast(r);
            while (nums[maxDeq.peekFirst()] - nums[minDeq.peekFirst()] > limit) {
                if (l == maxDeq.peekFirst()) {
                    maxDeq.pollFirst();
                }
                if (l == minDeq.peekFirst()) {
                    minDeq.pollFirst();
                }
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
