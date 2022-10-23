"""
Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

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

hints:
1 Use dynamic programming.
2 The state of the DP can be the current index and the remaining characters to delete.
3 Having a prefix sum for each character can help you determine for a certain character c in some specific range, how many characters you need to delete to merge all occurrences of c in that range
"""
from functools import lru_cache


class StringCompressionII:
    def getLengthOfOptimalCompression(self, s: str, k: int) -> int:

        @lru_cache(None)
        def dfs(start, pre_char, pre_char_cnt, remain_delete):
            if remain_delete < 0:
                return float('inf')
            if start >= len(s):
                return 0
            if s[start] == pre_char:
                inc = 1 if pre_char_cnt == 1 or pre_char_cnt == 9 or pre_char_cnt == 99 else 0
                return inc + dfs(start + 1, pre_char, pre_char_cnt + 1, remain_delete)
            else:
                keep = 1 + dfs(start + 1, s[start], 1, remain_delete)
                delete = dfs(start + 1, pre_char, pre_char_cnt, remain_delete - 1)
                return min(keep, delete)

        return dfs(0, '', 0, k)