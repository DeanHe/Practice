package contest;
/*
You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
Return the rearranged number with minimal value.
Note that the sign of the number does not change after rearranging the digits.

Example 1:
Input: num = 310
Output: 103
Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
The arrangement with the smallest value that does not contain any leading zeros is 103.

Example 2:
Input: num = -7605
Output: -7650
Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
The arrangement with the smallest value that does not contain any leading zeros is -7650.


Constraints:
-10^15 <= num <= 10^15

hint
1 For positive numbers, the leading digit should be the smallest nonzero digit. Then the remaining digits follow in ascending order.
2 For negative numbers, the digits should be arranged in descending order.
 */
public class SmallestValueOfTheRearrangedNumber {
    public long smallestNumber(long num) {
        int[] cnt = new int[10];
        boolean positive = num >= 0;
        num = Math.abs(num);
        while(num > 0){
            int d = (int)(num % 10);
            cnt[d]++;
            num /= 10;
        }
        long res = 0;
        if(positive){
            for(int d = 1; d <= 9; d++){
                if(cnt[d] > 0){
                    cnt[d]--;
                    res = d;
                    break;
                }
            }
            for(int d = 0; d <= 9; d++){
                while(cnt[d] > 0){
                    res = res * 10 + d;
                    cnt[d]--;
                }
            }
            return res;
        } else {
            for(int d = 9; d >= 0; d--){
                while(cnt[d] > 0){
                    res = res * 10 + d;
                    cnt[d]--;
                }
            }
            return -res;
        }
    }
}
