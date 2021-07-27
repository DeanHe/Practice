package dp;
/*
Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.



Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
Example 2:

Input: n = 1
Output: 2
Example 3:

Input: n = 2
Output: 3


Constraints:

1 <= n <= 10^9

analysis:
let a[i] be the count of integer of binary strings of length i  without consecutive ones and ended with 0
let b[i] be the count of integer binary strings of length i without consecutive ones and ended with 1
 */
public class NonnegativeIntegersWithoutConsecutiveOnes {
    public int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
        int len = sb.length();
        int[] a = new int[len];
        int[] b = new int[len];
        a[0] = 1;
        b[0] = 1;
        for(int i = 1; i < len; i++){
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }
        int res = a[len - 1] + b[len - 1];
        for(int i = 1; i< len; i++){
            if(sb.charAt(i) == '1' && sb.charAt(i - 1) == '1'){
                break;
            }
            if(sb.charAt(i) == '0' && sb.charAt(i - 1) == '0'){
                res -= b[len - 1 - i];
            }
        }
        return res;
     }
}

