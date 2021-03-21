package array;

import java.util.*;

/*

// https://www.lintcode.com/problem/two-sum-less-than-or-equal-to-target/description

Given an array of integers, find how many pairs in the array such that their sum is less than or equal to a specific target number.
Please return the number of pairs.
*/
public class TwoSumLessThanOrEqualToTarget {
	/**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        int start = 0; 
        int end = len - 1;
        while(start < end){
            if(nums[start] + nums[end] <= target){
                count += end - start;
                start++;
            } else {
                end--;
            }
        }
        return count;
    }
}
