package dp.TimeSequenceType;
/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order,
lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

analysis:
use binary search for TC O(nlogn)
array lis saves the current lis ended with nums[i]

*/
public class LongestIncreasingSubsequence {
	/**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        // dp[i] means the LIS ends in nums[i]
        int[] dp = new int[len];
        int res = 1;
        for(int i = 0; i < len; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequenceII(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int[] lis = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++){
            int idx = binarySearch(lis, 0, res - 1, nums[i]);
            lis[idx] = nums[i];
            //System.out.println(Arrays.toString(lis));
            if(idx == res){
                res++;
            }
        }
        return res;
    }

    private int binarySearch(int[] arr, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
