package dp.TimeSequenceType;

import java.util.*;

/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 10^9
All the integers in nums are unique.

similar to Longest Increasing subsequence
1.sort all array elements in increasing order. The purpose of sorting is to make sure that all divisors of an element appear before it.
2.Create an array divCount[] of same size as input array. divCount[i] stores size of divisible subset ending with arr[i] (In sorted array). The minimum value of divCount[i] would be 1.
3.Traverse all array elements. For every element, find a divisor arr[j] with largest value of divCount[j] and store the value of divCount[i] as divCount[j] + 1.

TC O(N ^ 2)
*/
public class LargestDivisibleSubset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
        int len = nums.length;
        int[] dp = new int[len]; // means max length of subset ending with nums[i]
        int[] pre = new int[len]; // the previous index of element i in the largestDivisibleSubset ends with element i
        int maxLen = 0, endIdx = -1;
        Arrays.sort(nums);
        for(int i = 0; i < len; i++){
        	dp[i] = 1;
        	pre[i] = -1;
        	for(int j = i - 1; j >= 0; j++){
        		if(nums[i] % nums[j] == 0){
        			if(dp[j] + 1 > dp[i]){
        				dp[i] = dp[j] + 1;
        				pre[i] = j;
        			}
        		}
        	}
        	if(dp[i] > maxLen){
        		maxLen = dp[i];
        		endIdx = i;
        	}
        }
        while(endIdx != -1){
        	res.add(0, nums[endIdx]);
        	endIdx = pre[endIdx];
        }
        return res;
    }
}
