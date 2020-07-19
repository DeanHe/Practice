package Palindrome;

/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
*/
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int len = s.length();
        for (int l = 0; l < len / 2; l++) {
            int r = len - l - 1;
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindromeRange(s, l + 1, r) || isPalindromeRange(s, l, r - 1);
            }
        }
        return true;
    }

    public boolean isPalindromeRange(String s, int l, int r) {
        for (int i = l; i <= l + (r - l) / 2; i++) {
            if (s.charAt(i) != s.charAt(r - i + l)) {
                return false;
            }
        }
        return true;
    }
}
