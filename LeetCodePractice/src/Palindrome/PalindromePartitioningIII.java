package Palindrome;

import java.util.*;

/*
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.

Example 1:
Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.

Example 2:
Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.

Example 3:
Input: s = "leetcode", k = 8
Output: 0

Constraints:
1 <= k <= s.length <= 100.
s only contains lowercase English letters.
*/
public class PalindromePartitioningIII {
    public int palindromePartition(String s, int k) {
        if (s == null || s.length() <= k) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int len = s.length();
        int[][] minChange = new int[len][len]; //minChange[i][j] means the minimum change to make string s[i:j] palindrome
        for (int l = 2; l <= len; l++) {
            for (int i = 0, j = i + l - 1; j < len; i++, j++) {
                if (arr[i] != arr[j]) {
                    minChange[i][j] = minChange[i + 1][j - 1] + 1;
                } else {
                    minChange[i][j] = minChange[i + 1][j - 1];
                }
            }
        }
        int[][] dp = new int[k + 1][len];  //dp[i][j] means the minimum # of change for string s[0:j] with i group
        //init
        for (int j = 0; j < len; j++) {
            dp[1][j] = minChange[0][j];
        }
        //transfer func
        for (int p = 2; p <= k; p++) {
            for (int j = p - 1; j < len; j++) {
                dp[p][j] = Integer.MAX_VALUE;
                for (int i = p - 1; i <= j; i++) {
                    dp[p][j] = Math.min(dp[p][j], dp[p - 1][i - 1] + minChange[i][j]);
                }
            }
        }
        return dp[k][len - 1];
    }
}
