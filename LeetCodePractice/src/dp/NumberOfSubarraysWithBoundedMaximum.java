package dp;
/*
We are given an array nums of positive integers, and two positive integers left and right (left <= right).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least left and at most right.

Example:
Input:
nums = [2, 1, 4, 3]
left = 2
right = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Note:

left, right, and nums[i] will be an integer in the range [0, 109].
The length of nums will be in the range of [1, 50000].

analysis:
dp[i] means the number of subarray ended in nums[i] with maximum bound between left and right
 */
public class NumberOfSubarraysWithBoundedMaximum {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int len = nums.length, res = 0, pre = -1;
        int[] dp = new int[len + 1];
        for(int i = 0; i < len; i++){
            if(nums[i] < left){
                dp[i + 1] = dp[i];
            } else if(nums[i] > right){
                pre = i;
                dp[i + 1] = 0;
            } else {
                dp[i + 1] += i - pre;
            }
            res += dp[i + 1];
        }
        return res;
    }
}

