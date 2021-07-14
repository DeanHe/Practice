package contest;

import java.util.Arrays;

/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")


Constraints:

3 <= s.length <= 10^5
s consists of only lowercase English letters.
 */
public class UniqueLengthThreePalindromicSubsequences {
    public int countPalindromicSubsequence(String s) {
        int[][] map = new int[26][2];
        for(int[] arr : map){
            Arrays.fill(arr, -1);
        }
        int len = s.length(), res = 0;
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(map[c - 'a'][0] == -1){
                map[c - 'a'][0] = i;
            }
            map[c - 'a'][1] = i;
        }
        for(int i = 0; i < 26; i++){
            if(map[i][0] != -1){
                int start = map[i][0];
                int end = map[i][1];
                if(start + 1 < end){
                    res += s.substring(start + 1, end).chars().distinct().count();
                }
            }
        }
        return res;
    }
}

