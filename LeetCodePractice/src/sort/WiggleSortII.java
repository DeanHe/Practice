package sort;

import java.util.Arrays;

/*Given an unsorted array nums, reorder it such that

nums[0] < nums[1] > nums[2] < nums[3]....
Example
Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].

Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Challenge
Can you do it in O(n) time and/or in-place with O(1) extra space?

Notice
You may assume all input has valid answer.*/
public class WiggleSortII {
	/*
	 * @param nums: A list of integers
	 * 
	 * @return: nothing
	 */
	public void wiggleSort(int[] nums) {
		// write your code here
		int len = nums.length;
		int midian = findKthLargest(nums, len / 2);
		int[] temp = new int[len];
		Arrays.fill(temp, midian);
		int left = 1;
		int right = len - 1;
		if (len % 2 == 0) {
			right = len - 2;
		}
		for (int i = 0; i < len; i++) {
			if (nums[i] < midian) {
				temp[right] = nums[i];
				right -= 2;
			} else if (nums[i] > midian) {
				temp[left] = nums[i];
				left += 2;
			}
		}
		for (int i = 0; i < len; i++) {
			nums[i] = temp[i];
		}
	}

	private int findKthLargest(int[] arr, int K) {
		return findKthLargest(arr, 0, arr.length - 1, K);
	}

	private int findKthLargest(int[] arr, int start, int end, int K) {
		int pos = partition(arr, start, end);
		if (pos == arr.length - 1 - K) {
			return arr[pos];
		} else if (pos < arr.length - 1 - K) {
			return findKthLargest(arr, pos + 1, end, K);
		} else {
			return findKthLargest(arr, start, pos - 1, K);
		}
	}

	private int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		int pos = start;
		for (int i = start; i < end; i++) {
			if (arr[i] <= pivot) {
				swap(arr, pos++, i);
			}
		}
		swap(arr, pos, end);
		return pos;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
