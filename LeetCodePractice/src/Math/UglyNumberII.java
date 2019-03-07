package Math;

import java.util.ArrayList;

/*Ugly number is a number that only have factors 2, 3 and 5.
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
Note that 1 is typically treated as an ugly number.*/
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
		int i = 0, j = 0, k = 0, cand1, cand2, cand3, min;
		for (int m = 0; m < n; m++) {
			cand1 = ls.get(i) * 2;
			cand2 = ls.get(j) * 3;
			cand3 = ls.get(k) * 5;
			min = Math.min(cand1, Math.min(cand2, cand3));
			ls.add(min);
			if (min == cand1) {
				i++;
			}
			if (min == cand2) {
				j++;
			}
			if (min == cand3) {
				k++;
			}
		}
		return ls.get(n - 1);
	}
}
