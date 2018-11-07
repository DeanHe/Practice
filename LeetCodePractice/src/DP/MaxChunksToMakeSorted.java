package DP;

/*Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].*/

//https://leetcode.com/problems/max-chunks-to-make-sorted/discuss/113528/Simple-Java-O(n)-Solution-with-detailed-explanation
public class MaxChunksToMakeSorted {
	public int maxChunksToSorted(int[] arr) {
		int ans = 0, max = 0, len = arr.length;
		for (int i = 0; i < len; i++) {
			max = Math.max(max, arr[i]);
			if (max == i) {
				ans++;
			}
		}
		return ans;
	}

	public int maxChunksToSortedDP(int[] arr) {
		if (arr == null || arr.length == 0)
			return 0;

		int[] max = new int[arr.length];
		max[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max[i] = Math.max(max[i - 1], arr[i]);
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (max[i] == i) {
				count++;
			}
		}

		return count;
	}
}
