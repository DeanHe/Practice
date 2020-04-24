package PrefixSum;
/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum >= s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

tag: two pointer
*/
public class MInimumSizeSubarraySum {
	public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length, preSum = 0, start = 0;
        int minLen = Integer.MAX_VALUE;
        for(int end = 0; end < len; end++){
            preSum += nums[end];
            while(preSum >= s){
                minLen = Math.min(minLen, end - start + 1);
                preSum -= nums[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
