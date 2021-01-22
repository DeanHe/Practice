package BinarySearch;
/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/
public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
		int[] res = {-1, -1};
		if (nums == null || nums.length == 0) {
			return res;
		}
		res[0] = binarySearch(nums, target, true);
		res[1] = binarySearch(nums, target, false);
		return res;
	}

	private int binarySearch(int[] nums, int target, boolean leftward) {
		int len = nums.length;
		int s = 0, e = len - 1;
		while (s + 1 < e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				if (leftward) {
					e = m;
				} else {
					s = m;
				}
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		if (leftward) {
			if (nums[s] == target) {
				return s;
			}
			if (nums[e] == target) {
				return e;
			}
		} else {
			if (nums[e] == target) {
				return e;
			}
			if (nums[s] == target) {
				return s;
			}
		}
		return -1;
	}
}
