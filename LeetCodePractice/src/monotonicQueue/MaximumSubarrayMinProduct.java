package monotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/*
The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.

Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,3,2]
Output: 14
Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
2 * (2+3+2) = 2 * 7 = 14.
Example 2:

Input: nums = [2,3,3,1,2]
Output: 18
Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
3 * (3+3) = 3 * 6 = 18.
Example 3:

Input: nums = [3,1,5,6,4,2]
Output: 60
Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
4 * (5+6+4) = 4 * 15 = 60.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^7
 */
public class MaximumSubarrayMinProduct {
    int MOD = (int)(1e9 + 7);
    public int maxSumMinProduct(int[] nums) {
        long res = 0;
        int len = nums.length;
        long[] preSum = new long[len + 1];
        preSum[0] = nums[0];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] left = previousSmallerElement(nums);
        int[] right = nextSmallerElement(nums);
        for(int i = 0; i < len; i++){
            int l = left[i];
            int r = right[i];
            long sum = (preSum[r + 1] - preSum[l]) * nums[i];
            res = Math.max(res, sum);
        }
        return (int)(res % MOD);
    }

    private int[] nextSmallerElement(int[] nums){
        int len = nums.length;
        int[] res = new int[len];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < len; i++){
            while(!deque.isEmpty() && nums[deque.peekLast()] > nums[i]){
                int pre = deque.pollLast();
                res[pre] = i - 1;
            }
            deque.offerLast(i);
        }
        while(!deque.isEmpty()){
            int pre = deque.pollLast();
            res[pre] = len - 1;
        }
        return res;
    }

    private int[] previousSmallerElement(int[] nums){
        int len = nums.length;
        int[] res = new int[len];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = len - 1; i >= 0; i--){
            while(!deque.isEmpty() && nums[deque.peekLast()] > nums[i]){
                int pre = deque.pollLast();
                res[pre] = i + 1;
            }
            deque.offerLast(i);
        }
        while(!deque.isEmpty()){
            int pre = deque.pollLast();
            res[pre] = 0;
        }
        return res;
    }
}

