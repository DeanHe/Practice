package twoPointers;

/*Give two sorted arrays. To take a number from each of the two arrays, the sum of the two numbers needs to be less than or equal to k, and you need to find the index combination with the largest sum of the two numbers. Returns a pair of indexes containing two arrays. If you have multiple index answers with equal sum of two numbers, you should choose the index pair with the smallest index of the first array.

The sum of the two numbers <= k
The sum is the biggest
The first array index is the smallest
Example
Example1

A = [1, 4, 6, 9], B = [1, 2, 3, 4], K = 9
return [2, 2]
Example2:

Input: 
A = [1, 4, 6, 8], B = [1, 2, 3, 5], K = 12
Output:
[2, 3]*/
public class OptimalUtilization {
	/**
	 * @param A:
	 *            a integer sorted array
	 * @param B:
	 *            a integer sorted array
	 * @param K:
	 *            a integer
	 * @return: return a pair of index
	 */
	public int[] optimalUtilization(int[] A, int[] B, int K) {
		// write your code here
		int[] res = { -1, -1 };
		int temp = Integer.MIN_VALUE;
		int j = B.length - 1;
		for (int i = 0; i < A.length; i++) {
			while (j >= 0) {
				if (A[i] + B[j] <= K) {
					if (temp < A[i] + B[j]) {
						temp = A[i] + B[j];
						while (j > 0 && B[j] == B[j - 1]) {
							j--;
						}
						res[0] = i;
						res[1] = j;
						while (i + 1 < A.length && A[i] == A[i + 1]) {
							i++;
						}
					}
					break;
				} else {
					j--;
				}
			}
		}
		if (res[0] == -1) {
			return new int[0];
		}
		return res;
	}
}
