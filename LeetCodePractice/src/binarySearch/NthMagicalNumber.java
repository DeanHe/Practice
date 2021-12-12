package binarySearch;
/*A positive integer is magical if it is divisible by either A or B.
Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: N = 1, A = 2, B = 3
Output: 2

Example 2:
Input: N = 4, A = 2, B = 3
Output: 6

Example 3:
Input: N = 5, A = 2, B = 4
Output: 10

Example 4:
Input: N = 3, A = 6, B = 4
Output: 8

Note:

1 <= N <= 10^9
2 <= A <= 40000
2 <= B <= 40000

analysis:
Math; binary search
*/
public class NthMagicalNumber {
	int MOD = (int) (1e9 + 7);

	public int nthMagicalNumber(int n, int a, int b) {
		if (b < a) {
			return nthMagicalNumber(n, b, a);
		}
		long s = 0, e = Long.MAX_VALUE;
		while (s < e) {
			long mid = s + (e - s) / 2;
			if (check(a, b, mid) < n) {
				s = mid + 1;
			} else {
				e = mid;
			}
		}
		return (int) (s % MOD);
	}

	private long check(int a, int b, long mid) {
		long cnt = 0;
		int lcm = leastCommonMultiple(a, b);
		cnt += (mid / a);
		if (lcm != b) {
			cnt += (mid / b);
			cnt -= (mid / lcm);
		}
		return cnt;
	}

	private int leastCommonMultiple(int a, int b) {
		long leastCommonMultiple = a * b / greatestCommonDivisor(a, b);
		return (int) leastCommonMultiple;
	}

	private int greatestCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return greatestCommonDivisor(b, a % b);
		}
	}
}
