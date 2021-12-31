package Palindrome;

import dp.TwoSequenceType.LongestCommonSubsequence;

/*
Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

Example 1:
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.

Constraints:
1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length

Notes:
The key to this question is how to convert the question to an equivalent one.
If we have another string, t, which is just the string s in the reverse order, then this question becomes relevant to the longest common sequence.
If the length of lcs is no smaller than (len - k), it is true.

analysis:
longest common sequence (lcs)
TC O(N^2)
*/
public class ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        String rev = reverse(s);
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int cnt = lcs.longestCommonSubsequence(s, rev);
        return s.length() - cnt <= k;
    }

    private String reverse(String str){
        int s = 0, e = str.length() - 1;
        char[] arr = str.toCharArray();
        while(s < e){
           char tmp = arr[s];
           arr[s] = arr[e];
           arr[e] = tmp;
        }
        return String.valueOf(arr);
    }
}
