package BitManipulation;

import java.util.Arrays;

/*
Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.

Return the length of the maximum length awesome substring of s.



Example 1:

Input: s = "3242415"
Output: 5
Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
Example 2:

Input: s = "12345678"
Output: 1
Example 3:

Input: s = "213123"
Output: 6
Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
Example 4:

Input: s = "00"
Output: 2


Constraints:

1 <= s.length <= 10^5
s consists only of digits.

similar to:
1371. Find the Longest Substring Containing Vowels in Even Counts.

as there are only 0-9 digits, there will be 2^10 states
 */
public class FindLongestAwesomeSubstring {
    public int longestAwesome(String s) {
        int[] dp = new int[1024]; // dp[state] means the first position in s[:pos] of state
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int res = 0, state = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            state ^= 1 << (c - '0');
            for(int j = 0; j <= 9; j++){
                if(dp[state ^ (1 << j)] >= 0){
                    res  = Math.max(res, i + 1 - dp[state ^ (1 << j)]);
                }
            }
            if(dp[state] >= 0){
                res = Math.max(res, i + 1 - dp[state]);
            } else {
                dp[state] = i + 1;
            }
        }
        return res;
    }
}
