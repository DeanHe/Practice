package BinarySearch;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?

The only difference is that due to the existence of duplicates, we can have nums[left] == nums[mid] and in that case,
the first half could be out of order (i.e. NOT in the ascending order, e.g. [3 1 2 3 3 3 3]) and we have to deal this case separately. 
In that case, it is guaranteed that nums[right] also equals to nums[mid], so what we can do is to check if nums[mid]== nums[left] == nums[right] before the original logic, 
and if so, we can move left and right both towards the middle by 1. and repeat.
*/
public class SearchInRotatedSortedArrayII {
	public boolean search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int start = 0;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (nums[mid] == target) {
				return true;
			} else if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
				start++;
				end--;
			} else if (nums[start] <= nums[mid]) {
				if (nums[start] <= target && target <= nums[mid]) {
					end = mid;
				} else {
					start = mid;
				}
			} else {
				if (nums[mid] <= target && target <= nums[end]) {
					start = mid;
				} else {
					end = mid;
				}
			} 
		}
		if (nums[start] == target) {
			return true;
		}
		if (nums[end] == target) {
			return true;
		}
		return false;
	}
}
