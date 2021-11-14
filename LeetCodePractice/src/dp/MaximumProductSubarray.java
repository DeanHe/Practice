package dp;
/*
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

It is guaranteed that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


Constraints:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/
public class MaximumProductSubarray {
	/**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
    	int len = nums.length;
    	int[] p = new int[len];
    	int[] n = new int[len];
    	int res = Integer.MIN_VALUE;
    	for(int i = 0; i < len; i++){
    		p[i] = nums[i];
    		n[i] = nums[i];
    		if(i > 0){
    			p[i] = Math.max(p[i], p[i - 1] * nums[i]);
    			p[i] = Math.max(p[i], n[i - 1] * nums[i]);
    			n[i] = Math.min(n[i], p[i - 1] * nums[i]);
    			n[i] = Math.min(n[i], n[i - 1] * nums[i]);
    		}
    		res = Math.max(p[i], res);
    	}
    	return res;
    }
}
