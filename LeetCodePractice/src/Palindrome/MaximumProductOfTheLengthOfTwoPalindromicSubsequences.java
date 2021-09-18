package Palindrome;

/*
Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.



Example 1:

example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.
Example 2:

Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.
Example 3:

Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.

Constraints:

2 <= s.length <= 12
s consists of lowercase English letters only.

hint:
Could you generate all possible pairs of disjoint subsequences?
Could you find the maximum length palindrome in each subsequence for a pair of disjoint subsequences?
 */
public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
    public int maxProduct(String s) {
        return dfs(s, "", "", 0);
    }

    private int dfs(String s, String s1, String s2, int i) {
        int res = 0;
        if (isPalindrome(s1) && isPalindrome(s2)) {
            res = s1.length() * s2.length();
        }
        if (i == s.length()) {
            return res;
        }
        char c = s.charAt(i);
        res = Math.max(res, dfs(s, s1, s2, i + 1));
        res = Math.max(res, dfs(s, s1 + c, s2, i + 1));
        res = Math.max(res, dfs(s, s1, s2 + c, i + 1));
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

