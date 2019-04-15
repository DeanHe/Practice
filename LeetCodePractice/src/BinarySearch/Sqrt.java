package BinarySearch;
/*mplement int sqrt(int x).

Compute and return the square root of x.

Example
sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

Challenge
O(log(x))*/
public class Sqrt {
	/**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
    	if(x <= 0){
    		return 0;
    	}
    	if(x == 1){
    		return 1;
    	}
    	int mid, start = 1, end = x;
    	while(start + 1 < end){
    		mid = start + (end - start) / 2;
    		if(mid * mid == x){
    			return mid;
    		} else if(mid * mid < x){
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	if(end * end <= x){
    		return (int)end;
    	} else {
    		return (int)start;
    	}
    }
}
