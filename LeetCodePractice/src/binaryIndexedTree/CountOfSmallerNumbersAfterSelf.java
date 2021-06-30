package binaryIndexedTree;

import java.util.*;

/*
You are given an integer arr nums and you have to return a new counts arr.
The counts arr has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

analysis:
discretization puts numbers of nums array in continuous rank without gap

*/
public class CountOfSmallerNumbersAfterSelf {
	/**
	 * @param nums:
	 *            a list of integers
	 * @return: return a list of integers
	 */
	int[] bit;
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int len = nums.length;
		bit = new int[len + 1];

		discretization(nums);
		for (int i = len - 1; i >= 0; i--) {
			int count = getPrefixSum(nums[i] - 1);
			res.add(0, count);
			update(nums[i]);
		}
		return res;
	}

	// this is nlogn
	// sort the orignal arr and mapping the number to
	// the order in the sorted arr;
	// i.e: [1, 1000, -100, 10, 100] -> [1, 4, 0, 2, 3] , get rid of very large
	// number or negative number
	private void discretization(int[] nums) {
		int[] sorted = nums.clone();
		Arrays.sort(sorted);
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Arrays.binarySearch(sorted, nums[i]);
		}
	}

	private void update(int index) {
		for (int i = index + 1; i < bit.length; i = i + lowbit(i)) {
			bit[i]++;
		}
	}

	private int getPrefixSum(int index) {
		int sum = 0;
		for (int i = index + 1; i > 0; i = i - lowbit(i)) {
			sum += bit[i];
		}
		return sum;
	}

	private int lowbit(int x) {
		return x & (-x);
	}
}
