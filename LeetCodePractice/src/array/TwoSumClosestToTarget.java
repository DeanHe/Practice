package array;

import java.util.Arrays;

/*Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.

Return the bsolute value of difference between the sum of the two integers and the target.

Example
Example1

Input:  nums = [-1, 2, 1, -4] and target = 4
Output: 1
Explanation:
The minimum difference is 1. (4 - (2 + 1) = 1).
Example2

Input:  nums = [-1, -1, -1, -4] and target = 4
Output: 6
Explanation:
The minimum difference is 6. (4 - (- 1 - 1) = 6).
Challenge
Do it in O(nlogn) time complexity.*/
public class TwoSumClosestToTarget {
	/**
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        // write your code here
    	if(nums == null || nums.length == 0){
    		return -1;
    	}
    	Arrays.sort(nums);
    	int len = nums.length;
    	int start = 0, end = len - 1;
    	int difference = Integer.MAX_VALUE;
    	while(start < end){
    		int sum = nums[start] + nums[end];
    		if(sum == target){
    			return 0;
    		} else if (sum > target){
    			end--;
    		} else {
    			start++;
    		}
    		difference = Math.min(difference, Math.abs(sum - target));
    	}
    	return difference;
    }
}
