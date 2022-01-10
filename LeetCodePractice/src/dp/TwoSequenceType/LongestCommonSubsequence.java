package dp.TwoSequenceType;

/*
Given two strings, find the longest common subsequence (LCS).
Your code should return the length of LCS.

Example 1:
Input:  "ABCD" and "EDCA"
Output:  1
Explanation:
LCS is 'A' or  'D' or 'C'

Example 2:
Input: "ABCD" and "EACB"
Output:  2
Explanation:
LCS is "AC" or "AB"

Analysis:
TC O(N*M)
*/
public class LongestCommonSubsequence {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        //mem[i][j] means the LCS length formed by A[:i] and B[:j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }
}
