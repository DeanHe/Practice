package PrefixSum;

import java.util.*;

/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example
Given nums = [1, -1, 5, -2, 3], k = 3, return 4.

Explanation:
because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
Given nums = [-2, -1, 2, 1], k = 1, return 2.

Explanation:
because the subarray [-1, 2] sums to 1 and is the longest.
Challenge
Can you do it in O(n) time?

Notice
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

similar SubarraySumEqualsK
*/
public class MaximumSizeSubarraySumEqualsK {
	/**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length, preSum = 0, res = 0;
        Map<Integer, Integer> preSumFirstEnd = new HashMap<>();
        preSumFirstEnd.put(preSum, -1);
        for(int end = 0; end < len; end++){
            preSum += nums[end];
            if(!preSumFirstEnd.containsKey(preSum)){
                preSumFirstEnd.put(preSum, end);
            }
            if(preSumFirstEnd.containsKey(preSum - k)){
                int firstEnd = preSumFirstEnd.get(preSum - k);
                res = Math.max(res, end - firstEnd);
            }
        }
        return res;
    }
}
