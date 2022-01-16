package Palindrome;
/*
You are given an array of strings words. Each element of words consists of two lowercase English letters.

Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.

Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.

A palindrome is a string that reads the same forward and backward.



Example 1:

Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.
Example 2:

Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.
Example 3:

Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx".


Constraints:
1 <= words.length <= 10^5
words[i].length == 2
words[i] consists of lowercase English letters.

hint:
1 A palindrome must be mirrored over the center. Suppose we have a palindrome. If we prepend the word "ab" on the left,
what must we append on the right to keep it a palindrome?
2 We must append "ba" on the right. The number of times we can do this is the minimum of (occurrences of "ab") and (occurrences of "ba").
3 For words that are already palindromes, e.g. "aa", we can prepend and append these in pairs as described in the previous hint.
We can also use exactly one in the middle to form an even longer palindrome.
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {
    public int longestPalindrome(String[] words) {
        int[][] cnt = new int[26][26];
        int res = 0;
        for(String w : words){
            int a = w.charAt(0) - 'a';
            int b = w.charAt(1) - 'a';
            if(cnt[b][a] > 0){
                cnt[b][a]--;
                res += 4;
            } else {
                cnt[a][b]++;
            }
        }
        for(int i = 0; i < 26; i++){
            if(cnt[i][i] > 0){
                res += 2;
                break;
            }
        }
        return res;
    }
}
