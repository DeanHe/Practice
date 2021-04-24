package math;

/*
Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.


Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true


Note:

1 <= N <= 10^9

analysis:
convert number to string sorted by digit
 */

import java.util.Arrays;

public class ReorderedPowerOfTwo {
    public boolean reorderedPowerOf2(int N) {
        char[] arr = String.valueOf(N).toCharArray();
        Arrays.sort(arr);
        String target = String.valueOf(arr);
        for(int i = 0; i < 31; i++){
            int powerTwo = 1 << i;
            arr = String.valueOf(powerTwo).toCharArray();
            Arrays.sort(arr);
            String tmp = String.valueOf(arr);
            if(tmp.equals(target)){
                return true;
            }
        }
        return false;
    }
}
