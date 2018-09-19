package BinarySearch;

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
