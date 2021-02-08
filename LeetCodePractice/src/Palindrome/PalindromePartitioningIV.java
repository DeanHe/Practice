package Palindrome;
/*
#5666
Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.



Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.


Constraints:

3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
 */
public class PalindromePartitioningIV {
    public boolean checkPartitioning(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (j + 1 > i - 1 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                }
            }
        }
        for(int i = 0; i < len - 2; i++){
            for(int j = i + 1; j < len - 1; j++){
                if(isPalindrome[0][i] && isPalindrome[i + 1][j] && isPalindrome[j + 1][len - 1]){
                    return true;
                }
            }
        }
        return false;
    }
}
