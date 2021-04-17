package dp.TwoSequenceType;

/*
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length();
        if (l1 + l2 != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;
        for (int i = 0; i < l1; i++) {
            if (s1.charAt(i) == s3.charAt(i) && dp[i][0]) {
                dp[i + 1][0] = true;
            }
        }
        for (int i = 0; i < l2; i++) {
            if (s2.charAt(i) == s3.charAt(i) && dp[0][i]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (s3.charAt(i + j + 1) == s1.charAt(i) && dp[i][j + 1]) {
                    dp[i + 1][j + 1] = true;
                }
                if (s3.charAt(i + j + 1) == s2.charAt(j) && dp[i + 1][j]) {
                    dp[i + 1][j + 1] = true;
                }
            }
        }
        return dp[l1][l2];
    }
}
