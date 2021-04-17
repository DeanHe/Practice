package dp.TwoSequenceType;

/*
    Given two strings, find the longest common substring.

        Return the length of it.

        Example
        Example 1:
        Input:  "ABCD" and "CBCE"
        Output:  2

        Explanation:
        Longest common substring is "BC"


        Example 2:
        Input: "ABCD" and "EACB"
        Output:  1

        Explanation:
        Longest common substring is 'A' or 'C' or 'B'
        Challenge
        O(n x m) time and memory.

        Notice
        The characters in substring should occur continuously in original string. This is different with subsequence.
*/
public class LongestCommonSubstring {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 0; i < m; i++) {
            char A_cur = A.charAt(i);
            for (int j = 0; j < n; j++) {
                char B_cur = B.charAt(j);
                if (A_cur == B_cur) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = 0;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
}
