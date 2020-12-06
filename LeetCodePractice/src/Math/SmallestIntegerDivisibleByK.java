package Math;
/*
Given a positive integer K, you need to find the length of the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.

Return the length of N. If there is no such N, return -1.

Note: N may not fit in a 64-bit signed integer.



Example 1:

Input: K = 1
Output: 1
Explanation: The smallest answer is N = 1, which has length 1.
Example 2:

Input: K = 2
Output: -1
Explanation: There is no such positive integer N divisible by 2.
Example 3:

Input: K = 3
Output: 3
Explanation: The smallest answer is N = 111, which has length 3.


Constraints:

1 <= K <= 10^5

analysis:
Furthermore, we can improve this algorithm with Pigeonhole Principle.
Recall that the number of possible values of remainder (ranging from 0 to K-1) is limited, and in fact, the number is K.
As a result, if the while-loop continues more than K times, and haven't stopped,
then we can conclude that remainder repeats -- you can not have more than K different remainder.
 */
public class SmallestIntegerDivisibleByK {
    public int smallestRepunitDivByK(int K) {
        int remainder = 0;
        for(int i = 1; i <= K; i++){
            remainder = (remainder * 10 + 1) % K;
            if(remainder == 0){
                return i;
            }
        }
        return -1;
    }
}
