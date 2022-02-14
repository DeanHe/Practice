package dividAndConquer;
/*
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.

Challenge
The overall run time complexity should be O(log (m+n)).
*/
public class MedianOfTwoSortedArrays {
	int aLen, bLen;
	public double findMedianSortedArrays(int A[], int B[]) {
		aLen = A.length;
		bLen = B.length;
		int len = aLen + bLen;
		if (len % 2 == 0) {
			// even
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
		} else {
			// odd
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
	}

	private double findKth(int[] A, int ai, int[] B, int bi, int K) {
		// K is count start from 1
		if (ai >= aLen) {
			return B[bi + K - 1];
		}
		if (bi >= bLen) {
			return A[ai + K - 1];
		}
		if (K == 1) {
			return Math.min(A[ai], B[bi]);
		}
		if (ai + K / 2 - 1 < aLen && bi + K / 2 - 1 < bLen) {
			int candA = A[ai + K / 2 - 1];
			int candB = B[bi + K / 2 - 1];
			if (candA < candB) {
				return findKth(A, ai + K / 2, B, bi, K - K / 2);
			} else {
				return findKth(A, ai, B, bi + K / 2, K - K / 2);
			}
		} else if (ai + K / 2 - 1 < aLen && bi + K / 2 - 1 >= bLen) {
			return findKth(A, ai + K / 2, B, bi, K - K / 2);
		} else {
			return findKth(A, ai, B, bi + K / 2, K - K / 2);
		}
	}
}
