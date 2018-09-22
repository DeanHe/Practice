package Array;

import java.util.*;

/*Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. Please return the number of pairs.*/
public class TwoSumUniquePairs {
	/**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        if(nums == null || nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int cnt = 0;
        int left = 0, right = nums.length - 1;
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum == target){
                cnt++;
                left++;
                right--;
                while(left < right && (nums[left] == nums[left - 1])){
                    left++;
                }
                while(left < right && (nums[right] == nums[right + 1])){
                    right--;
                }
            } else if(sum > target){
                right--;
            } else {
                left++;
            }
        }
        return cnt;
    }
}
