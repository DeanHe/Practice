package dp;

import java.util.*;

/*Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

Example
Example 1:

Input:
[1, 3, -1, 2, -1, 2]
Output:
7
Explanation:
the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2].
Example 2:

Input:
[5,4]
Output:
9
Explanation:
the two subarrays are [5] and [4].
Challenge
Can you do it in time complexity O(n) ?

Notice
The subarray should contain at least one number*/
public class MaximumSubarrayII {
	/**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0){
            return 0;
        }
        int len = nums.size();
        // left is max sum of possible subarray within array from 0 to i
        int[] left = new int[len];
        int[] right = new int[len];
        // maxSum is the max sum of subarray ended with nums[i]
        int maxSum = nums.get(0);
        left[0] = maxSum;
        for(int i = 1; i < len; i++){
            maxSum = Math.max(nums.get(i), maxSum + nums.get(i));
            left[i] = Math.max(left[i-1], maxSum);
            
        }
        //reset
        maxSum = nums.get(len-1);
        right[len-1] = maxSum;
        for(int i = len - 2; i >= 0; i--){
            maxSum = Math.max(nums.get(i), nums.get(i) + maxSum);
            right[i] = Math.max(right[i+1], maxSum);
        }
        
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < len-1; i++){
            res = Math.max(res, left[i] + right[i+1]);
        }
        return res;
    }
}
