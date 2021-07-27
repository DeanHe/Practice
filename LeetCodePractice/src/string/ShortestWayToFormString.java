package string;

import java.util.Arrays;

/*
From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

Example 1:

Input: source = “abc”, target = “abcbc”
Output: 2
Explanation: The target “abcbc” can be formed by “abc” and “bc”, which are subsequences of source “abc”.
Example 2:

Input: source = “abc”, target = “acdbc”
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character “d” in target string.
Example 3:

Input: source = “xyz”, target = “xzyxz”
Output: 3
Explanation: The target string can be constructed as follows “xz” + “y” + “xz”.
Constraints:

Both the source and target strings consist of only lowercase English letters from “a”-“z”.
The lengths of source and target string are between 1 and 1000.

TC O(M + N)
SC O(M)
 */
public class ShortestWayToFormString {
    public int shortestWay(String source, String target) {
        int slen = source.length(), tlen = target.length();
        int[][] next = new int[slen][26];
        next[slen - 1][source.charAt(slen - 1) - 'a'] = slen;
        for(int i = slen - 2; i >= 0; i--){
            next[i] = Arrays.copyOf(next[i + 1], 26);
            next[i][source.charAt(i) - 'a'] = i + 1;
        }
        int j = 0, res = 0;
        for(int i = 0; i < tlen; i++){
            if(next[0][target.charAt(i) - 'a'] == 0){
                return -1;
            }
            if(j == slen){
                j = 0;
                res++;
            }
            j = next[j][target.charAt(i) - 'a'];
            if(j == 0){
                res++;
                i--;
            }
        }
        return res;
    }
}

