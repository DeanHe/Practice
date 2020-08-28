package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers and an integer k, you need to find the minimum size of continuous subarrays whose sum equals to k, and return its length.

if there are no such subarray, return -1.

Example
Example1

Input: nums = [1,1,1,2] and k = 3
Output: 2
Example2

Input: nums = [2,1,-1,4,2,-3] and k = 3
Output: 2
Notice
the integer nums[i] may lower than 0
 */
public class SubarraySumEqualsKII {
    /**
     * @param nums: a list of integer
     * @param k:    an integer
     * @return: return an integer, denote the minimum length of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsKII(int[] nums, int k) {
        // write your code here
        int res = Integer.MAX_VALUE;
        int len = nums.length;
        int sum = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, -1);
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                res = Math.min(res, i - preSum.get(sum - k) + 1);
            }
            preSum.put(sum, i);
        }
        return res;
    }
}
