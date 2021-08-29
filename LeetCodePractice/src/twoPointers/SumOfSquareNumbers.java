package twoPointers;
/*
Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.



Example 1:

Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:

Input: c = 3
Output: false
Example 3:

Input: c = 4
Output: true
Example 4:

Input: c = 2
Output: true
Example 5:

Input: c = 1
Output: true


Constraints:

0 <= c <= 2^31 - 1
 */
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        int s = 0, e = (int)Math.sqrt(c);
        while(s <= e){
            int sum = s * s + e * e;
            if(sum == c){
                return true;
            } else if(sum < c){
                s++;
            } else {
                e--;
            }
        }
        return false;
    }
}

