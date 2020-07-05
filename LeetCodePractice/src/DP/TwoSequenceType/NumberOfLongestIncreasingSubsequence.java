package DP.TwoSequenceType;
/*Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.*/
public class NumberOfLongestIncreasingSubsequence {
	public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        //dp_longest[i] means LIS length ended with nums[i]
        int[] dp_longest = new int[len];
      //dp_longest[i] means # of LIS ended with nums[i]
        int[] dp_count = new int[len];
        for(int i = 0; i < len; i++){
        	dp_count[i] = 1;
        }
        for(int i = 0; i < len; i++){
        	for(int j = 0; j < i; j++){
        		if(nums[i] > nums[j]){
        			if(dp_longest[i] == dp_longest[j] + 1){
        				dp_count[i] += dp_count[j];
        			} else if(dp_longest[i] < dp_longest[j] + 1){
        				dp_count[i] = dp_count[j];
        				dp_longest[i] = dp_longest[j] + 1;
        			}
        		}
        	}
        }
        int longest = 0;
        for(int l : dp_longest){
        	longest = Math.max(longest, l);
        }
        int res = 0;
        for(int i = 0; i < len; i++){
        	if(dp_longest[i] == longest){
        		res += dp_count[i];
        	}
        }
        String s = 1 + "" + 2;
        return res;
    }
}
