package binarySearch;

import java.util.Arrays;

/*
Give you two arrays, the first array may contains repeatable integers, The second array is a subset of the first array.The length of the returned array is the same as the second array. For each element a in the second array, how many numbers are in the first array <=a.

Example
Example 1:

Input:  nums = [3, 2, 4, 3, 5, 1],sub = [2, 4]
Output:  [2, 5] 
Explanation： <=2 numbers are [1,2]，<=4 numbers are [1,2,3,3,4]
Example 2:

Input: nums = [3, 1, 2, 3, 3, 1],sub = [1,3]
Output：[2, 6] 
Explanation：<=1 numbers are [1,1]，<=3 numbers are [1,1,2,3,3,3]
*/
public class SimpleQueries {
	/**
	 * @param nums:
	 * @param sub:
	 * @return: return a Integer array
	 */
	public int[] SimpleQueries(int[] nums, int[] sub) {
		// write your code here
		int lenN = nums.length;
		int lenS = sub.length;
		Arrays.sort(nums);
		int min = nums[0];
		int max = nums[lenN - 1];
		int[] res = new int[lenS];
		for (int i = 0; i < lenS; i++) {
			if (sub[i] < min) {
				res[i] = 0;
			} else if (sub[i] > max) {
				res[i] = lenN;
			} else {
				int idx = binarySearch(nums, sub[i]);
				while (idx < lenN && nums[idx] == sub[i]) {
					idx++;
				}
				res[i] = idx;
			}

		}
		return res;
	}
	private int binarySearch(int[] nums, int target){
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while(start + 1 < end){
			mid = start + (end - start) / 2;
			if(nums[mid] == target){
				return mid;
			} else if(nums[mid] < target){
				start = mid;
			} else {
				end = mid;
			}
		}
		if(nums[start] < target){
			return end;
		}
		return start;
	}
}
