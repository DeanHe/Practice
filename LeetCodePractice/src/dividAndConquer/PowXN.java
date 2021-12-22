package dividAndConquer;
/*
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= xn <= 10^4

analysis:
TC O(log N)
 */
public class PowXN {
	public double pow(double x, int n) {
		if (x == 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}

		double half = pow(x, n / 2);
		 // n is even , then x^n=x^n/2*x^n/2
		if (n % 2 == 0) {
			return half * half;
		} else if (n > 0) {
		//  n>0 and n is odd, x^n=x^n/2 * x^n/2 *x
			return half * half * x;
		} else {
	    // n<0 and n is odd, x^n=x^n/2 *x^n/2 *x^-1
			return half * half / x;
		}
	}
}
