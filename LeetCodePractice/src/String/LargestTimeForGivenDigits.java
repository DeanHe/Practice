package String;

/*Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9

The inner most loop at most iterates 4 * 4 * 4 = 64 times.
A[i], A[j], A[k], & A[l] are the 4 elements of A, where i, j, k & l are the permutation of index 0, 1, 2, & 3.
Therefore, since index sum i + j + k + l = 0 + 1 + 2 + 3 = 6, we have l = 6 - i - j - k.
*/
public class LargestTimeForGivenDigits {
	public String largestTimeFromDigits(int[] A) {
		int res = -1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (i == j || i == k || j == k) {
						continue; // avoid duplicate among i, j & k.
					}
					int l = 6 - i - j - k;
					int hours = 10 * A[i] + A[j];
					int minutes = 10 * A[k] + A[l];
					if (hours < 24 && minutes < 60) {
						res = Math.max(res, 60 * hours + minutes);
					}
				}
			}
		}
		return res >= 0 ? String.format("%02d:%02d", res / 60, res % 60) : "";
	}
}
