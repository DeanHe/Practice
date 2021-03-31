package twoPointers.window;
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.

Example
Example 1:

Input: [2,3,1,2,4,3], s = 7
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: [1, 2, 3, 4, 5], s = 100
Output: -1
Challenge
If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).

tag: two pointer
*/
public class MinimumSizeSubarraySum {
	public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length, start = 0, end = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(end < len){
            sum += nums[end];
            end++;
            while(sum >= s){
                minLen = Math.min(minLen, end - start);
                sum -= nums[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
