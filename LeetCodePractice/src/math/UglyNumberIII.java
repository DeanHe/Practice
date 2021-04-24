package math;

/*Write a program to find the n-th ugly number.

Ugly numbers are positive integers which are divisible by a or b or c.

 

Example 1:

Input: n = 3, a = 2, b = 3, c = 5
Output: 4
Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
Example 2:

Input: n = 4, a = 2, b = 3, c = 4
Output: 6
Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
Example 3:

Input: n = 5, a = 2, b = 11, c = 13
Output: 10
Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
Example 4:

Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
Output: 1999999984
 

Constraints:

1 <= n, a, b, c <= 10^9
1 <= a * b * c <= 10^18
It's guaranteed that the result will be in range [1, 2 * 10^9]
*/public class UglyNumberIII {
	int max = 2 * 1000000000;
	public int nthUglyNumber(int n, int a, int b, int c) {
		int start = 1, end = max, res = 0;
		while(start <= end){
			int mid = start + (end - start) / 2;
			if(count(mid, a, b, c) >= n){
				res = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return res;
	}

	private int count(int num, int a, int b, int c) {
		return (int) (num / a + num / b + num / c - num / lcm(a, b) - num / lcm(b, c) - num / lcm(a, c)
				+ num / lcm(a, lcm(b, c)));
	}

	// greatest common divider
	private long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

	// least common multiple
	private long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}
}
