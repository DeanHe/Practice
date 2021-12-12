package stack.monotonicStack;

import java.util.Stack;

/*
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:
Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:
Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.

Constraints:
1 <= nums.length <= 1000
-10^9 <= nums[i] <= 10^9

hint:
 1 Can you get the max/min of a certain subarray by using the max/min of a smaller subarray within it?
 2 Notice that the max of the subarray from index i to j is equal to max of (max of the subarray from index i to j-1) and nums[j].

 analysis:
 SumOfSubarrayRanges is sum of all subarrays with A[i] as the maximum - subarrays with A[i] as the minimum
 for calculating the min part, maintain a monotonic increasing stack
 for calculating the max part, maintain a monotonic decreasing stack

TC O(N) SC O(N)

similar to
907. Sum of Subarray Minimums
901. Online Stock Span
 */
public class SumOfSubarrayRanges {
    public long subArrayRanges(int[] nums) {
        int len = nums.length, j = 0, k = 0;
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        // nums[j] as the minimum between nums[k : i]
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                j = stack.pop();
                if (!stack.isEmpty()) {
                    k = stack.peek();
                } else {
                    k = -1;
                }
                res -= (long) nums[j] * (i - j) * (j - k);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            j = stack.pop();
            if (!stack.isEmpty()) {
                k = stack.peek();
            } else {
                k = -1;
            }
            res -= (long) nums[j] * (len - j) * (j - k);
        }
        stack.clear();
        // nums[j] as the maximum between nums[k : i]
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                j = stack.pop();
                if (!stack.isEmpty()) {
                    k = stack.peek();
                } else {
                    k = -1;
                }
                res += (long) nums[j] * (i - j) * (j - k);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            j = stack.pop();
            if (!stack.isEmpty()) {
                k = stack.peek();
            } else {
                k = -1;
            }
            res += (long) nums[j] * (len - j) * (j - k);
        }
        return res;
    }
}
