package BinarySearch;
/*Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]*/
public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] A, int target) {
		int[] res = { -1, -1 };
		if (A == null || A.length == 0) {
			return res;
		}
		// binary search find left bound
		// the leftLow is the final index of target
		int leftLow = 0;
		int leftHigh = A.length - 1;
		while (leftLow <= leftHigh) {
			int mid = (leftLow + leftHigh) / 2;
			if (A[mid] >= target) {
				leftHigh = mid - 1;
			} else {
				leftLow = mid + 1;
			}
		}
		// binary search find right bound
		// the rightHigh is the final index of target
		int rightLow = 0;
		int rightHigh = A.length - 1;
		while (rightLow <= rightHigh) {
			int mid = (rightLow + rightHigh) / 2;
			if (A[mid] <= target) {
				rightLow = mid + 1;
			} else {
				rightHigh = mid - 1;
			}
		}
		if(leftLow <= rightHigh){
			res[0] = leftLow;
			res[1] = rightHigh;
		}
		return res;
	}
}
