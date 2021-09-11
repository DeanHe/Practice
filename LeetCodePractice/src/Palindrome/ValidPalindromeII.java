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

TC O(N)
*/
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length, l = 0, r = len - 1;
        while(l < r){
            if(arr[l] != arr[r]){
                return isPalindromeRange(arr, l + 1, r) || isPalindromeRange(arr, l, r - 1);
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public boolean isPalindromeRange(char[] arr, int l, int r) {
        while(l < r){
            if(arr[l] != arr[r]){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
