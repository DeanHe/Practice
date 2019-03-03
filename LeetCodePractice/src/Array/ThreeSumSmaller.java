package Array;

import java.util.Arrays;

/*Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example
Given nums = [-2,0,1,3], target = 2, return 2.

Explanation:
Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]*/
public class ThreeSumSmaller {
	/**
     * @param nums:  an array of n integers
     * @param target: a target
     * @return: the number of index triplets satisfy the condition nums[i] + nums[j] + nums[k] < target
     */
    public int threeSumSmaller(int[] nums, int target) {
        // Write your code here
    	Arrays.sort(nums);
    	int len = nums.length, count = 0;
    	for(int i = 0; i < len - 2; i++){
    		for(int j = i + 1, k = len - 1; j < k; j++){
    			while(j < k && nums[i] + nums[j] + nums[k] >= target){
    				k--;
    			}
    			if(nums[i] + nums[j] + nums[k] < target){
    				count += k - j;
    			}
    		}
    	}
    	return count;
    }
}
