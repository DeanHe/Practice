package Palindrome;
/*
You are given two strings, word1 and word2. You want to construct a string in the following manner:

Choose some non-empty subsequence subsequence1 from word1.
Choose some non-empty subsequence subsequence2 from word2.
Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.

A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.

A palindrome is a string that reads the same forward as well as backward.



Example 1:

Input: word1 = "cacb", word2 = "cbba"
Output: 5
Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
Example 2:

Input: word1 = "ab", word2 = "ab"
Output: 3
Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
Example 3:

Input: word1 = "aa", word2 = "bb"
Output: 0
Explanation: You cannot construct a palindrome from the described method, so return 0.


Constraints:

1 <= word1.length, word2.length <= 1000
word1 and word2 consist of lowercase English letters.

hints:
Let's ignore the non-empty subsequence constraint.
We can concatenate the two strings and find the largest palindromic subsequence with dynamic programming.

Iterate through every pair of characters word1[i] and word2[j],
and see if some palindrome begins with word1[i] and ends with word2[j]. This ensures that the subsequences are non-empty.
 */
public class MaximizePalindromeLengthFromSubsequences {
    public int longestPalindrome(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length(), len = len1 + len2, res = 0;
        String str = word1 + word2;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;
            res = 1;
        }
        for(int i = 0; i + 1 < len; i++){
            if(str.charAt(i) == str.charAt(i + 1)){
                dp[i][i + 1] = 2;
                if(i + 1 == len1){
                    res = 2;
                }
            } else {
                dp[i][i + 1] = 1;
            }
        }
        for(int l = 3; l <= len; l++){
            for(int i = 0; i + l - 1 < len; i++){
                int j = i + l - 1;
                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    if(i < len1 && j >= len1){
                        res = Math.max(res, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }
            }
        }
        return res;
    }
}
