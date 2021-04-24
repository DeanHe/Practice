package math;

import java.util.ArrayList;

/*
Ugly number is a number that only have factors 2, 3 and 5.
Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

Example
Example 1:

Input: 9
Output: 10
Example 2:

Input: 1
Output: 1
Challenge
O(n log n) or O(n) time.

Notice
Note that 1 is typically treated as an ugly number.

The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
*/
public class UglyNumberII {
	/**
	 * @param n:
	 *            An integer
	 * @return: return a integer as description.
	 */
	public int nthUglyNumber(int n) {
		// write your code here
		if (n <= 0) {
			return 0;
		}
		ArrayList<Integer> ls = new ArrayList<>();
		ls.add(1);
		int i = 0, j = 0, k = 0, cand1, cand2, cand3, tempMin;
		for (int m = 0; m < n; m++) {
			cand1 = ls.get(i) * 2;
			cand2 = ls.get(j) * 3;
			cand3 = ls.get(k) * 5;
			tempMin = Math.min(cand1, Math.min(cand2, cand3));
			ls.add(tempMin);
			if (tempMin == cand1) {
				i++;
			}
			if (tempMin == cand2) {
				j++;
			}
			if (tempMin == cand3) {
				k++;
			}
		}
		return ls.get(n - 1);
	}
}
