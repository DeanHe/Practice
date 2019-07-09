package DividAndConquer;
/*There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.

Challenge
The overall run time complexity should be O(log (m+n)).*/
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int A[], int B[]) {
		int len = A.length + B.length;
		if (len % 2 == 0) {
			// even
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
		} else {
			// odd
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
	}

	private double findKth(int[] A, int aStart, int[] B, int bStart, int K) {
		// K is count start from 1
		int aLen = A.length;
		int bLen = B.length;
		if (aStart >= aLen) {
			return B[bStart + K - 1];
		}
		if (bStart >= bLen) {
			return A[aStart + K - 1];
		}
		if (K == 1) {
			return Math.min(A[aStart], B[bStart]);
		}
		if (aStart + K / 2 - 1 < aLen && bStart + K / 2 - 1 < bLen) {
			int candA = A[aStart + K / 2 - 1];
			int candB = B[bStart + K / 2 - 1];
			if (candA < candB) {
				return findKth(A, aStart + K / 2, B, bStart, K - K / 2);
			} else {
				return findKth(A, aStart, B, bStart + K / 2, K - K / 2);
			}
		} else if (aStart + K / 2 - 1 < aLen && bStart + K / 2 - 1 >= bLen) {
			return findKth(A, aStart + K / 2, B, bStart, K - K / 2);
		} else {
			return findKth(A, aStart, B, bStart + K / 2, K - K / 2);
		}
	}
}
