package BinarySearch;
/*Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
*/
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
			sign = 1;
		} else {
			sign = -1;
		}
		long ldividend = Math.abs((long) dividend);
		long ldivisor = Math.abs((long) divisor);
		long res = ldivide(ldividend, ldivisor);
        if(res > Integer.MAX_VALUE){
            if(sign == 1){
                res = Integer.MAX_VALUE;
            } else {
                res = Integer.MIN_VALUE;
            }
        } else {
            res = sign * res;
        }
        return (int)res;
	}
    private long ldivide(long ldividend, long ldivisor){
        if(ldividend < ldivisor){
            return 0;
        }
        long sum = ldivisor;
        long multiple = 1;
        while(ldividend >= sum * 2){
            sum = sum * 2;
            multiple = multiple * 2;
        }
        return multiple + ldivide(ldividend - sum, ldivisor);
    }
}
