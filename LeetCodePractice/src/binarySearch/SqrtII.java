package binarySearch;
/*
Implement double sqrt(double x) and x >= 0.

Compute and return the square root of x.
Example
sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

Challenge
O(log(x))
*/
public class SqrtII {
	/**
     * @param x: A double
     * @return: The sqrt of x
     */
    public double sqrt(double x) {
        // write your code here
		double mid, start = 0, end = x, eps = 1e-12;
		end = Math.max(x, 1.0); // to cover case x is a decimal, like x = 0.04 = 0.2 * 0.2
    	while(start + eps < end){
    		mid = start + (end - start) / 2;
    		if(mid * mid == x){
    			return mid;
    		} else if(mid * mid < x){
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	return start;
    }
}
