package Contest;

import java.util.*;

/*You are given a string s containing lowercase letters and an integer k. You need to :

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
    Map<String, Integer> mem = new HashMap<>();

    public int palindromePartition(String s, int k) {
        if (s == null || s.length() <= k) {
            return 0;
        }
        int len = s.length();
        int[][] dp = new int[k][len + 1];  //dp[i][j] means the minimum # of change for string s[0:j) with i cut
        for (int i = 1; i <= len; i++) {
            dp[0][i] = minChangeToPalin(s.substring(0, i));
        }
        for (int i = 1; i < k; i++) {
            for (int j = i; j <= len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int p = j; p >= i; p--) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][p - 1] + minChangeToPalin(s.substring(p - 1, j)));
                }
            }
        }
        return dp[k - 1][len];
    }

    private int minChangeToPalin(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (mem.containsKey(s)) {
            return mem.get(s);
        }
        int res = 0;
        int len = s.length();
        int mid = len / 2;
        char[] arr = s.toCharArray();
        for (int i = 0; i < mid; i++) {
            if (arr[i] != arr[len - 1 - i]) {
                res++;
            }
        }
        mem.put(s, res);
        return res;
    }
}
