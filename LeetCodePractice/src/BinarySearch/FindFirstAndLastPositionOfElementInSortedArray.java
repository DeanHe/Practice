package BinarySearch;

public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] A, int target) {
		int[] res = { -1, -1 };
		if (A == null || A.length == 0) {
			return res;
		}
		// binary search find left bound
		// the leftLow is the final index of target
		int leftLow = 0;
		int leftHigh = A.length - 1;
		while (leftLow <= leftHigh) {
			int mid = (leftLow + leftHigh) / 2;
			if (A[mid] >= target) {
				leftHigh = mid - 1;
			} else {
				leftLow = mid + 1;
			}
		}
		// binary search find right bound
		// the rightHigh is the final index of target
		int rightLow = 0;
		int rightHigh = A.length - 1;
		while (rightLow <= rightHigh) {
			int mid = (rightLow + rightHigh) / 2;
			if (A[mid] <= target) {
				rightLow = mid + 1;
			} else {
				rightHigh = mid - 1;
			}
		}
		if(leftLow <= rightHigh){
			res[0] = leftLow;
			res[1] = rightHigh;
		}
		return res;
	}
}
