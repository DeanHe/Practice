package binarySearch;

/*
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

        Given n, find the total number of full staircase rows that can be formed.

        n is a non-negative integer and fits within the range of a 32-bit signed integer.

        Example 1:

        n = 5

        The coins can form the following rows:
        ¤
        ¤ ¤
        ¤ ¤

        Because the 3rd row is incomplete, we return 2.
        Example 2:

        n = 8

        The coins can form the following rows:
        ¤
        ¤ ¤
        ¤ ¤ ¤
        ¤ ¤

        Because the 4th row is incomplete, we return 3.
*/

public class ArrangingCoins {
    public int arrangeCoins(int n) {
        long s = 0, e = n;
        long sum = 0, mid = 0;
        while(s + 1 < e){
            mid = s + (e - s) / 2;
            sum = (1 + mid) * mid / 2;
            if(sum == n){
                return (int)mid;
            } else if(sum < n){
                s = mid;
            } else {
                e = mid;
            }
        }
        sum = (1 + e) * e / 2;
        if(sum == n){
            return (int)e;
        }
        return (int)s;
    }
}
