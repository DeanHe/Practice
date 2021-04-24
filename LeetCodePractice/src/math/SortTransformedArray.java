package math;

/*Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Example
Example1

Input: nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5
Output: [3, 9, 15, 33]
Example2

Input: nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
Output: [-23, -5, 1, 7]
Notice
Expected time complexity: O(n)*/
public class SortTransformedArray {
	/**
	 * @param nums:
	 *            a sorted array
	 * @param a:
	 * @param b:
	 * @param c:
	 * @return: a sorted array
	 */
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		// Write your code here
		int len = nums.length;
		int[] res = new int[len];
		int start = 0, end = len - 1;
		int idx = 0;
		if (a >= 0) {
			idx = len - 1;
		}
		while (start <= end) {
			int startVal = transform(nums[start], a, b, c);
			int endVal = transform(nums[end], a, b, c);
			if (a >= 0) {
				if (startVal >= endVal) {
					res[idx] = startVal;
					start++;
				} else {
					res[idx] = endVal;
					end--;
				}
				idx--;
			} else {
				if (startVal <= endVal) {
					res[idx] = startVal;
					start++;
				} else {
					res[idx] = endVal;
					end--;
				}
				idx++;
			}
		}
		return res;
	}

	private int transform(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}
}
