  package binarySearch;
/*
#162
A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


Constraints:

1 <= nums.length <= 1000
-2^31 <= nums[i] <= 2^31 - 1
nums[i] != nums[i + 1] for all valid i.

Challenge
Time complexity O(logN).
*/
public class FindPeakElement {
	/**
     * @param nums: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] nums) {
        int len = nums.length;
        int start = 0, end = len - 1, mid;
        while(start + 1 < end){
        	mid = start + (end - start) / 2;
        	if(nums[mid] < nums[mid + 1]){
        		start = mid;
        	} else {
        		end = mid;
        	}
        }
        if(nums[start] < nums[end]){
        	return end;
        } else {
        	return start;
        }
    }
}
