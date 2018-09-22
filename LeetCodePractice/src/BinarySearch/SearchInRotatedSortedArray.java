package BinarySearch;

public class SearchInRotatedSortedArray {
	public int search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}
		int low = 0;
		int high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (target < A[mid]) {
				// right side in order
				if (A[mid] < A[high]) {
					high = mid - 1;
				}
				// left side in order
				else {
					if (target >= A[low]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}
			}
			// target > A[mid]
			else {
				// left side in order
				if (A[low] < A[mid]) {
					low = mid + 1;
				}
				// right side in order
				else {
					if (target <= A[high]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}
			}
		}
		return -1;
	}
}
