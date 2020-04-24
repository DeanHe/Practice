package PrefixSum;

import java.util.*;

/*
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.

Example
Example1

Input: nums = [1,1,1] and k = 2
Output: 2
Explanation:
subarray [0,1] and [1,2]
Example2

Input: nums = [2,1,-1,1,2] and k = 3
Output: 4
Explanation:
subarray [0,1], [1,4], [0,3] and [3,4]
*/
public class SubarraySumEqualsK {
	/**
     * @param nums: a list of integer
     * @param k: an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsK (int[] nums, int k) {
		int len = nums.length, res = 0, preSum = 0;
		Map<Integer, Integer> preSumCnt = new HashMap<>(); // prefixSum : count of end position of preSum[:end] == prefixSum;
		preSumCnt.put(0, 1);
		for(int i = 0; i < len; i++){
			preSum += nums[i];
			if(preSumCnt.containsKey(preSum - k)){
				res += preSumCnt.get(preSum - k);
			}
			preSumCnt.put(preSum, preSumCnt.getOrDefault(preSum, 0) + 1);
		}
		return res;
    }
}
