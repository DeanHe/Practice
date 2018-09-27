package Array;

//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
// https://leetcode.com/problems/merge-sorted-array/description/
public class MergeSortedArray {
	public void merge(int A[], int m, int B[], int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (A[i] < B[j]) {
				A[k--] = B[j--];
			} else {
				A[k--] = A[i--];
			}

		}
		while (j >= 0) {
			A[k--] = B[j--];
		}
	}
}
