package dividAndConquer;

/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.

TC: O(NlogN)
*/
public class CountOfRangeSum {
	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int[] prefixSum = new int[len + 1];
		int[] temp = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		return countWhileMergeSort(prefixSum, 0, len, temp, lower, upper);
	}

	private int countWhileMergeSort(int[] preSum, int start, int end, int[] temp, int lower, int upper) {
		if (start >= end) {
			return 0;
		}
		int mid = start + (end - start) / 2;
		int count = countWhileMergeSort(preSum, start, mid, temp, lower, upper)
				+ countWhileMergeSort(preSum, mid + 1, end, temp, lower, upper);
		int l = start, r = start;
		for (int i = mid + 1; i <= end; i++) {
			while (l <= mid && preSum[i] - preSum[l] > upper) {
				l++;
			}
			while (r <= mid && preSum[i] - preSum[r] >= lower) {
				r++;
			}
			count += r - l;
		}
		merge(preSum, start, mid, end, temp);
		return count;
	}

	private void merge(int[] A, int start, int mid, int end, int[] temp) {
		int left = start;
		int right = mid + 1;
		int i = start;
		while (left <= mid && right <= end) {
			if (A[left] < A[right]) {
				temp[i++] = A[left++];
			} else {
				temp[i++] = A[right++];
			}
		}
		while (left <= mid) {
			temp[i++] = A[left++];
		}
		while (right <= end) {
			temp[i++] = A[right++];
		}
		for (i = start; i <= end; i++) {
			A[i] = temp[i];
		}
	}
}
