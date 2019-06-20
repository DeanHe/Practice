package BinarySearch;
/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1*/
public class SearchInRotatedSortedArray {
	public int search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}
		int start = 0;
		int end = A.length - 1;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[start] < A[mid]) {
				// left side from beginning in order
				if(A[start] <= target && target <= A[mid]){
					end = mid;
				} else {
					start = mid;
				}
			} else {
				// right side to end in order
				if (A[mid] <= target && target <= A[end]) {
					start = mid;
				}
				else {
					end = mid;
				}
			}
		}
		if(A[start] == target){
			return start;
		}
		if(A[end] == target){
			return end;
		}
		return -1;
	}
}
