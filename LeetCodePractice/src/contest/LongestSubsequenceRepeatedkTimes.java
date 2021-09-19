package contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k times in string s.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a string constructed by concatenating seq k times.

For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba", constructed by concatenating "bba" 2 times, is a subsequence of the string "bababcba".
Return the longest subsequence repeated k times in string s. If multiple such subsequences are found, return the lexicographically largest one. If there is no such subsequence, return an empty string.



Example 1:

example 1
Input: s = "letsleetcode", k = 2
Output: "let"
Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
"let" is the lexicographically largest one.
Example 2:

Input: s = "bb", k = 2
Output: "b"
Explanation: The longest subsequence repeated 2 times is "b".
Example 3:

Input: s = "ab", k = 2
Output: ""
Explanation: There is no subsequence repeated 2 times. Empty string is returned.
Example 4:

Input: s = "bbabbabbbbabaababab", k = 3
Output: "bbbb"
Explanation: The longest subsequence "bbbb" is repeated 3 times in "bbabbabbbbabaababab".


Constraints:

n == s.length
2 <= k <= 2000
2 <= n < k * 8
s consists of lowercase English letters.

hint:
The length of the longest subsequence does not exceed n/k. Do you know why?

Find the characters that could be included in the potential answer.
A character occurring more than or equal to k times can be used in the answer up to (count of the character / k) times.

Try all possible candidates in reverse lexicographic order, and check the string for the subsequence condition.

analysis:
from constraint that k > n/8, which means longest valid subsequence is 7 characters

BFS try all possible subsequence
TC O(n * 7!)
 */
public class LongestSubsequenceRepeatedkTimes {
    public String longestSubsequenceRepeatedK(String s, int k) {
        String res = "";
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
            cnt[c - 'a']++;
        }
        List<Character> candidateLetters = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            if(cnt[i] >= k){
                candidateLetters.add((char)('a' + i));
            }
        }
        Queue<String> q = new LinkedList<>();
        q.offer("");
        while (!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                String cand = q.poll();
                for(char c : candidateLetters){
                    String next = cand + c;
                    if (validSubsequence(s, next, k)){
                        res = next;
                        q.offer(next);
                    }
                }
            }
        }
        return res;
    }

    private boolean validSubsequence(String s, String sub, int k){
        // TC O(n)
        int sLen = s.length(), subLen = sub.length(), repeat = 0, j = 0;
        for(int i = 0; i < sLen; i++){
            if(sLen - i < (k - repeat - 1) * subLen){
                return false;
            }
            if(s.charAt(i) == sub.charAt(j)){
                j++;
            }
            if(j == subLen){
                repeat++;
                if(repeat == k){
                    return true;
                }
                j = 0;
            }
        }
        return false;
    }
}

