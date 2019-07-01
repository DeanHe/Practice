package Array;

import java.util.Arrays;

/*Given two arrays, write a function to compute their intersection.

Example
Example 1:

Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2], 
Output: [2].
Example 2:

Input: nums1 = [1, 2], nums2 = [2], 
Output: [2].
Challenge
Can you implement it in three different algorithms?

Notice
Each element in the result must be unique.
The order of the results needs to be ascending*/
public class IntersectionOfTwoArrays {
	/*
	 * @param nums1: an integer array
	 * 
	 * @param nums2: an integer array
	 * 
	 * @return: an integer array
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		int len1 = nums1.length, len2 = nums2.length, i = 0, j = 0, idx = 0;
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] temp = new int[len1];
		while (i < len1 && j < len2) {
			if (nums1[i] == nums2[j]) {
				if (idx == 0 || temp[idx - 1] != nums1[i]) {
					temp[idx++] = nums1[i];
				}
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] res = new int[idx];
		for (i = 0; i < idx; i++) {
			res[i] = temp[i];
		}
		return res;
	}
}
