package PrefixSum;

import java.util.*;

/*Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

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
subarray [0,1], [1,4], [0,3] and [3,4]*/
public class SubarraySumEqualsK {
	/**
     * @param nums: a list of integer
     * @param k: an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsK (int[] nums, int k) {
    	int len = nums.length;
    	int res = 0;
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // prefixSum : count;
    	map.put(0, 1);
    	int[] preSum = new int[len];
    	preSum[0] = nums[0];
    	for(int i = 1; i < len; i++){
    		preSum[i] = preSum[i - 1] + nums[i];
    	}
    	for(int i = 0; i < len; i++){
    		if(map.containsKey(preSum[i] - k)){
    			res += map.get(preSum[i] - k);
    		}
    		map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
    	}
    	return res;
    }
}
