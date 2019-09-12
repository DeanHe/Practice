package Array;
/*Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.*/
public class MaximumSubarray {
	public int[] maxSubArray(int[] nums) {
        int len = nums.length;
        int temp = Integer.MIN_VALUE;
        int[] res = new int[2];
        int start = 0;
        int sum = 0;
        for(int end = 0; end < len; end++){
            sum += nums[end];
            if(temp < sum){
            	temp = sum;
            	res[0] = start;
            	res[1] = end;
            }
            if(sum < 0){
                sum = 0;
                start = end + 1;
            }
        }
        return res;
    }
	
	public int maxSubArrayDP(int[] nums) {
        int max = nums[0];
		int[] dp = new int[nums.length];
		dp[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
            if(dp[i - 1] > 0){
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
			if (max < dp[i]) {
				max = dp[i];
			}
		}

		return max;
    }
}
