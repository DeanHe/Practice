package dp;

/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such minimum-length windows, return the one with the smallest starting index.

Example 1:
Input：S="jmeqksfrsdcmsiwvaovztaqenprpvnbstl"，T="u"
Output：""
Explanation： unable to match

Example 2:
Input：S = "abcdebdde"， T = "bde"
Output："bcde"
Explanation："bcde" is the answer and "deb" is not a smaller window because the elements of T in the window must occur in order.
Notice
All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

solution:
different from MinimumWindowSubstring by requiring the T sequence match

mem[i + 1][j + 1] = k, i为S的index，j为T的index，k为以[0,i]，[0,j]这2段substring最小的起点在s上
if s[j] == t[i], mem[i][j] hen can reuse mem[i - 1][j - 1] as start position
if s[j] ！= t[i]，then can reuse mem[i][j - 1]

analysis:
TC O(S*T)
*/
public class MinimumWindowSubsequence {
    /**
     * @param s: a string
     * @param t: a string
     * @return: the minimum substring of S
     */
    public String minWindow(String s, String t) {
        String res = "";
        int sLen = s.length(), tLen = t.length(), minLen = sLen;
        Integer[][] dp = new Integer[sLen + 1][tLen + 1]; // mem[i + 1][j + 1] means the start position of s[:i] contains sequence of t[:j]
        // init
        for (int i = 0; i < sLen; i++) {
            dp[i][0] = i;
        }
        //transfer
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j <= Math.min(i, tLen - 1); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
            }
            if (dp[i + 1][tLen] != null) {
                if (minLen > i - dp[i + 1][tLen] + 1) {
                    res = s.substring(dp[i + 1][tLen], i + 1);
                    minLen = i - dp[i + 1][tLen] + 1;
                }
            }
        }
        return res;
    }

    public String minWindowII(String s, String t) {
        String res = "";
        int sLen = s.length(), tLen = t.length(), minLen = sLen, start = -1;
        int[][] dp = new int[sLen][tLen]; // mem[i][j] means the start position of s[:i] contains sequence of t[:j]
        // init
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                dp[i][0] = i;
            } else if (i > 0) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = -1;
            }
        }
        for (int i = 1; i < tLen; i++) {
            dp[0][i] = -1;
        }
        //transfer
        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < tLen; j++) {
                if (i < j) {
                    dp[i][j] = -1;
                } else {
                    if (s.charAt(i) == t.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < sLen; i++) {
            if (dp[i][tLen - 1] != -1) {
                if (minLen > i - dp[i][tLen - 1] + 1) {
                    res = s.substring(dp[i][tLen - 1], i + 1);
                    minLen = i - dp[i][tLen - 1] + 1;
                }
            }
        }
        return res;
    }
}
