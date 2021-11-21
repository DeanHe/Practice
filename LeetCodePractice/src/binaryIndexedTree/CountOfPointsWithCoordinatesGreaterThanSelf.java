package binaryIndexedTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given n points in the 2D plane, where each point has a unique coordinate [x, y].
return the counts of points with greater coordinate than itself.

Example:

Input: (4,3),(5,1),(2,4),(1,0),(3,2)
output: [0, 0, 0, 4, 1]
sort by x descending y increasing (5,1),(4,3),(3,2),(2,4),(1,0)
then apply <count of greater number before itself on Y>

Google
TC O(Nlog N)
*/
public class CountOfPointsWithCoordinatesGreaterThanSelf {
	/**
	 * @param nums:
	 *            a list of integers
	 * @return: return a list of integers
	 */
	int[] bit;
	public int[] countGreaterPoints(int[][] points) {
		int len = points.length;
		bit = new int[len + 1];
		int[] res = new int[len];
		int[][] arr = new int[len][3];
		for(int i = 0; i < len; i++){
			arr[i][0] = points[i][0];
			arr[i][1] = points[i][1];
			arr[i][2] = i;
		}
		Arrays.sort(arr, (a, b) ->{
			if(a[0] != b[0]){
				return b[0] - a[0];
			}
			return a[1] - b[1];
		});
		normalizeY(arr);
		for(int i = 0; i < len; i++){
			int idx = arr[i][2];
			res[idx] = i - getPrefixSum(arr[i][1] - 1);
			update(arr[i][1]);
		}
		return res;
	}

	private void normalizeY(int[][] nums) {
		int len = nums.length;
		int[] sorted = new int[len];
		for(int i = 0; i < len; i++){
			sorted[i] = nums[i][1];
		}
		Arrays.sort(sorted);
		for (int i = 0; i < nums.length; i++) {
			nums[i][1] = Arrays.binarySearch(sorted, nums[i][1]);
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
