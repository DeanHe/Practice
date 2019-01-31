package Array;
/*Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number. Please return the number of pairs.

Example
Given numbers = [2, 7, 11, 15], target = 24. Return 1. (11 + 15 is the only pair)

Challenge
Do it in O(1) extra space and O(nlogn) time.*/
public class TwoSumGreaterThanTarget {
	/**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: an integer
     */
    public int twoSum2(int[] nums, int target) {
        // write your code here
    	int count = 0;
    	if(nums == null || nums.length < 2){
    		return count;
    	}
    	int len = nums.length;
    	int start = 0, end = len - 1;
    	while(start < end){
    		int sum = nums[start] + nums[end];
    		if(sum > target){
    			count += end - start;
    			start++;
    		} else {
    			end--;
    		}
    	}
    	return count;
    }
}
