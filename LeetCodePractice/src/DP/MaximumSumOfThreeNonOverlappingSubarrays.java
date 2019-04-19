package DP;
/*In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).*/
public class MaximumSumOfThreeNonOverlappingSubarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int maxVal = Integer.MIN_VALUE;
        int len = nums.length;
        int[] res = new int[3];
        int[] sum = new int[len + 1];
        // left[i] is the start position of subarray of k with max sum in nums[0, i], it is in range [0, i - k]
        int[] left =new int[len];
        int[] right = new int[len];
        for(int i = 0; i < len; i++){
        	sum[i + 1] = nums[i] + sum[i];
        }
     // left[i] is the start position of subarray of k with max sum in nums[i, len - 1], it is in range [i, len - 1 - k]
        for(int i = k, total = sum[k] - sum[0]; i < len; i++){
        	if(sum[i + 1] - sum[i + 1 - k] > total){
        		left[i] = i + 1 - k;
            	total = sum[i + 1] - sum[i + 1 - k];
        	} else {
        		left[i] = left[i - 1];
        	}
        }
        // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
        for(int i = len - k - 1, total = sum[len] - sum[len - k]; i >= 0; i--){
        	if(sum[i + k] - sum[i] >= total){
        		total = sum[i + k] - sum[i];
        		right[i] = i;
        	} else {
        		right[i] = right[i + 1];
        	}
        }
        // check all possible middle interval
        for(int i = k; i <= len - 2 * k; i++){
        	int l = left[i - 1], r = right[i + k];
        	int total = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
        	if(total > maxVal){
        		maxVal = total;
        		res[0] = l;
        		res[1] = i;
        		res[2] = r;
        	}
        }
        return res;
    }
}
