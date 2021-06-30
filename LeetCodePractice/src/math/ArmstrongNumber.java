package math;

import java.util.ArrayList;
import java.util.List;

/*
The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.

Given a positive integer N, return true if and only if it is an Armstrong number.



Example 1:

Input: 153
Output: true
Explanation:
153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
Example 2:

Input: 123
Output: false
Explanation:
123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 = 36.


Note:

1 <= N <= 10^8
 */
public class ArmstrongNumber {
    public boolean isArmstrong(int N) {
        int sum = 0, len = 0;
        List<Integer> ls = new ArrayList<>();
        while(N > 0){
            ls.add(N % 10);
            N /= 10;
            len++;
        }
        for(int n : ls){
            sum += power(n, len);
        }
        return sum == N;
    }

    private int power(int a, int b) {
        int res = 1;
        for(int i = 0; i < b; i++){
            res *= a;
        }
        return res;
    }
}

