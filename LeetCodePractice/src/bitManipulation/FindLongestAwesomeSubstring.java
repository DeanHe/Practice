package bitManipulation;

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

analysis:
similar to:
1371. Find the Longest Substring Containing Vowels in Even Counts.

two scenarios:
1 all number appear even times, 2 one number appear odd times

for case 1 if one state occur twice at [i, j] means all digits in s[i + 1, j] appear even times, res = j - i
for case 2, we can flip
as there are only 0-9 digits, there will be 2^10 states
time complexity: O(10 * n)
space complexity O(2^10) = O(1)
 */
public class FindLongestAwesomeSubstring {
    public int longestAwesome(String s) {
        Integer[] dp = new Integer[1024]; // dp[state] means the first position in s[:pos] of state
        dp[0] = -1;
        int res = 0, state = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            state ^= 1 << (c - '0');
            if (dp[state] != null) {  // case 1
                res = Math.max(res, i - dp[state]);
            }
            for (int j = 0; j <= 9; j++) { // case 2
                if (dp[state ^ (1 << j)] != null) {
                    res = Math.max(res, i - dp[state ^ (1 << j)]);
                }
            }
            if (dp[state] == null) {
                dp[state] = i;
            }
        }
        return res;
    }
}
