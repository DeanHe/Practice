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
2 <= B <= 40000*/
public class NthMagicalNumber {
	public int nthMagicalNumber(int N, int A, int B) {
        long leastCommonMultiple = A * B / greateCommonDivisor(A, B);
        return (int) leastCommonMultiple;
    }
	private int greateCommonDivisor(int A, int B) {
		if(B == 0){
			return A;
		} else {
			return greateCommonDivisor(B, A % B);
		}
	}
}
