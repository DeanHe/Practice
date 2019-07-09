package DividAndConquer;
/*
return the Kth square number of a sorted array

test case:
KthSquareNumberOfSortedArray sol = new KthSquareNumberOfSortedArray();
int[] arr = { -5, -4, -3, -2, 1, 6, 7, 8 };
int res = sol.getKthSquareNumber(arr, 8);
System.out.println(res);
*/
public class KthSquareNumberOfSortedArray {
	public int getKthSquareNumber(int[] arr, int K){
		int positive = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] >= 0){
				positive = i;
				break;
			}
		}
		return findKth(arr, positive, positive - 1, K);
	}
	
	private int findKth(int[] arr, int aStart, int bStart, int K) {
		// K is count start from 1
		int len = arr.length;
		if (aStart >= len) {
			return arr[bStart - K + 1] * arr[bStart - K + 1];
		}
		if (bStart < 0) {
			return arr[aStart + K - 1] * arr[aStart + K - 1];
		}
		if (K == 1) {
			return Math.min(arr[aStart] * arr[aStart], arr[bStart] * arr[bStart]);
		}
		if (aStart + K / 2 - 1 < len && bStart - K / 2 + 1 >= 0) {
			int candA = arr[aStart + K / 2 - 1];
			candA = candA * candA;
			int candB = arr[bStart - K / 2 + 1];
			candB = candB * candB;
			if (candA < candB) {
				return findKth(arr, aStart + K / 2, bStart, K - K / 2);
			} else {
				return findKth(arr, aStart, bStart - K / 2, K - K / 2);
			}
		} else if (aStart + K / 2 - 1 < len && bStart - K / 2 + 1 < 0) {
			return findKth(arr, aStart + K / 2, bStart, K - K / 2);
		} else {
			return findKth(arr, aStart, bStart - K / 2, K - K / 2);
		}
	}
}
