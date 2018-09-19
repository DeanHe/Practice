package DividAndConquer;

public class MedianOfTwoSortedArrays {
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;

		int k = (m + n) / 2 + 1; // k传得是第k个，index实则k-1
		if ((m + n) % 2 != 0) {
			// odd
			return findKth(A, B, k, 0, m - 1, 0, n - 1);
		} else {
			// even
			return (findKth(A, B, k - 1, 0, m - 1, 0, n - 1) + findKth(A, B, k,
					0, m - 1, 0, n - 1)) / 2;
		}
	}
	
	public static double findKth(int[] A, int[] B, int k, int aStart, int aEnd,
			int bStart, int bEnd) {
		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;
		if (aLen > bLen) {
			// make sure alen < bLen
			return findKth(B, A, k, bStart, bEnd, aStart, aEnd);
		}
		if (aLen == 0) {
			return B[k - 1];
		}
		if (k == 1) {
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
		}

		int aMidCount = Math.min(k / 2, aLen);
		int bMidCount = k - aMidCount;

		if (A[aStart + aMidCount - 1] < B[bStart + bMidCount - 1]) {
			return findKth(A, B, k - aMidCount, aStart + aMidCount, aEnd,
					bStart, bEnd);
		} else if (A[aStart + aMidCount - 1] > B[bStart + bMidCount - 1]) {
			return findKth(A, B, k - bMidCount, aStart, aEnd, bStart
					+ bMidCount, bEnd);
		} else {
			// equal
			return A[aStart + aMidCount - 1];
		}
	}
}
