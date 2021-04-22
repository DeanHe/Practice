package dp;
/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
Example 1:

Input:[2,3,-2,4]
Output:6
Example 2:

Input:[-1,2,4,1]
Output:8
*/
public class MaximumProductSubarray {
	/**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
    	int len = nums.length;
    	int[] max = new int[len];
    	int[] min = new int[len];
    	int res = Integer.MIN_VALUE;
    	for(int i = 0; i < len; i++){
    		max[i] = nums[i];
    		min[i] = nums[i];
    		if(i > 0){
    			max[i] = Math.max(max[i], max[i - 1] * nums[i]);
    			max[i] = Math.max(max[i], min[i - 1] * nums[i]);
    			min[i] = Math.min(min[i], max[i - 1] * nums[i]);
    			min[i] = Math.min(min[i], min[i - 1] * nums[i]);
    		}
    		res = Math.max(max[i], res);
    	}
    	return res;
    }
}
