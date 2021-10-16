package contest;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.
Return the minimum possible absolute difference.

Example 1:

example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
Example 2:

Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
Example 3:

example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.

Constraints:

1 <= n <= 15
nums.length == 2 * n
-10^7 <= nums[i] <= 10^7

hint:
1 The target sum for the two partitions is sum(nums) / 2.
2 Could you reduce the time complexity if you arbitrarily divide nums into two halves (two arrays)? Meet-in-the-Middle?
3 For both halves, pre-calculate a 2D array where the kth index will store all possible sum values if only k elements from this half are added.
4 For each sum of k elements in the first half, find the best sum of n-k elements in the second half such that the two sums add up to a value closest to the target sum from hint 1. These two subsets will form one array of the partition.

analysis:
For N elements, there are 2**N subset possible. Then we also sum those each of those subset. O(N * 2^N)
This technique commonly known as Meet In Middle. Commonly used when 25 <= array_size <= 40
 */
public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    public int minimumDifference(int[] nums) {
        int len = nums.length, n = len / 2, res = 0;
        if(){

        }

    }
}
