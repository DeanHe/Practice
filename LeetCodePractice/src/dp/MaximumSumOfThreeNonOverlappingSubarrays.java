package dp;
/*
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.



Example 1:

Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] < 216
1 <= k <= floor(nums.length / 3)

analysis:
Time Complexity: O(N), where N is the length of the array.
Every loop is bounded in the number of steps by N, and does O(1) work.

Space complexity: O(N). preSum, left, and right all take O(N)O(N) memory.
*/
public class MaximumSumOfThreeNonOverlappingSubarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int maxVal = Integer.MIN_VALUE;
        int len = nums.length;
        int[] res = new int[3];
        int[] preSum = new int[len + 1];
        // left[i] is the start position of subarray of k with max sum in nums[0, i], it is in range [0, i - k]
        int[] left =new int[len];
        int[] right = new int[len];
        for(int i = 0; i < len; i++){
        	preSum[i + 1] = nums[i] + preSum[i];
        }
     // left[i] is the start position of subarray of k with max sum in nums[i, len - 1], it is in range [i, len - 1 - k]
        for(int i = k, most = preSum[k] - preSum[0]; i < len; i++){
        	if(preSum[i + 1] - preSum[i + 1 - k] > most){
        		left[i] = i + 1 - k;
            	most = preSum[i + 1] - preSum[i + 1 - k];
        	} else {
        		left[i] = left[i - 1];
        	}
        }
        // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
        for(int i = len - k - 1, most = preSum[len] - preSum[len - k]; i >= 0; i--){
        	if(preSum[i + k] - preSum[i] >= most){
        		most = preSum[i + k] - preSum[i];
        		right[i] = i;
        	} else {
        		right[i] = right[i + 1];
        	}
        }
        // check all possible middle interval
        for(int i = k; i <= len - 2 * k; i++){
        	int l = left[i - 1], r = right[i + k];
        	int total = (preSum[i + k] - preSum[i]) + (preSum[l + k] - preSum[l]) + (preSum[r + k] - preSum[r]);
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
