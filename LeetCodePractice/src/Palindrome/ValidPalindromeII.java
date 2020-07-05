package Palindrome;
/*Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.*/
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        for(int i = 0; i < s.length() / 2; i++){
            int j = s.length() - i - 1;
            if(s.charAt(i) != s.charAt(j)){
                return isPalindromeRange(s, i + 1, j) || isPalindromeRange(s, i, j - 1);
            }
        }
        return true;
    }
    public boolean isPalindromeRange(String s, int i, int j) {
        for(int k = i; k <= i + (j - i) / 2; k++){
            if(s.charAt(k) != s.charAt(j-k+i)){
                return false;
            }
        }
        return true;
    }
}
