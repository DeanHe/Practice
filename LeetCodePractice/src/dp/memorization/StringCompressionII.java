package dp.memorization;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run).

For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
Notice that in this problem, we are not adding '1' after single characters.
Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
Find the minimum length of the run-length encoded version of s after deleting at most k characters.



Example 1:
Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.

Example 2:
Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

Example 3:
Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.

Constraints:
1 <= s.length <= 100
0 <= k <= s.length
s contains only lowercase English letters.

analysis:
mem[i][j][l][m] means min length of compressed string of s[i:] with last char is j and current run-length is l, and still can delete m chars
 */
public class StringCompressionII {
    Integer[][][][] mem;
    int len;
    char[] arr;
    Set<Integer> carry;

    public int getLengthOfOptimalCompression(String s, int k) {
        carry = new HashSet<>(Arrays.asList(1, 9, 99));
        len = s.length();
        arr = s.toCharArray();
        mem = new Integer[len + 1][27][len + 1][k + 1];
        return dfs(0, (char) ('a' + 26), 0, k);
    }

    private int dfs(int idx, char pre, int preCnt, int del) {
        if (del < 0) {
            return Integer.MAX_VALUE;
        }
        if (idx == len) {
            return 0;
        }
        if (mem[idx][pre - 'a'][preCnt][del] != null) {
            return mem[idx][pre - 'a'][preCnt][del];
        }
        int res = 0;
        //case 1: delete the current char
        res = dfs(idx + 1, pre, preCnt, del - 1);
        //case 2: add the current char
        if (arr[idx] == pre) { // same as pre char
            res = Math.min(res, dfs(idx + 1, pre, preCnt + 1, del) + (carry.contains(preCnt) ? 1 : 0));
        } else { // not same as pre char
            res = Math.min(res, dfs(idx + 1, arr[idx], 1, del) + 1);
        }
        return mem[idx][pre - 'a'][preCnt][del] = res;
    }

}
