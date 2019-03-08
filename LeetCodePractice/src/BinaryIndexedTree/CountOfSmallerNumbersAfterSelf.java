package BinaryIndexedTree;

import java.util.*;

/*You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.*/
public class CountOfSmallerNumbersAfterSelf {
	/**
	 * @param nums:
	 *            a list of integers
	 * @return: return a list of integers
	 */
	int[] bit, count;

	public List<Integer> countSmaller(int[] nums) {
		// write your code here
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int len = nums.length;
		bit = new int[len + 1];
		count = new int[len];

		discretization(nums);
		for (int i = len - 1; i >= 0; i--) {
			count[i] = getPrefixSum(nums[i] - 1);
			update(nums[i]);
		}
		for (int i = 0; i < len; i++) {
			res.add(count[i]);
		}
		return res;
	}

	// this is nlogn
	// sort the orignal array and mapping the number to
	// the order in the sorted array;
	// i.e: [1, 1000, -100, 10, 100] -> [2, 5, 1, 3, 4] , get rid of very large
	// number or negative number
	private void discretization(int[] nums) {
		int[] sorted = nums.clone();
		Arrays.sort(sorted);
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Arrays.binarySearch(sorted, nums[i]) + 1;
		}
	}

	private void update(int index) {
		for (int i = index; i < bit.length; i = i + lowbit(i)) {
			bit[i]++;
		}
	}

	private int getPrefixSum(int index) {
		int sum = 0;
		for (int i = index; i > 0; i = i - lowbit(i)) {
			sum += bit[i];
		}
		return sum;
	}

	private int lowbit(int x) {
		return x & (-x);
	}
}
