package dp.TwoSequenceType;

/*
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.



Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4


Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.

analysis:

Same as LongestCommonSubsequence
 */
public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        // word1 - LCS + word2 - LCS
        return word1.length() + word2.length() - 2 * lcs(word1, word2);
    }

    private int lcs(String word1, String word2) {
        // write your code here
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //mem[i][j] means the LCS length formed by A[:i] and B[:j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }
}
