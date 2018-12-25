package TwoPointers;
/*Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum >= s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.*/
public class MInimumSizeSubarraySum {
	public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int i = 0, j = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(i < len){
            sum += nums[i];
            while(sum >= s){
                minLen = Math.min(minLen, i - j);
                sum -= nums[j];
                j++;
            }
            i++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
