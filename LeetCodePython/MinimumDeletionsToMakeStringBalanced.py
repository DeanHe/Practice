"""
You are given a string s consisting only of characters 'a' and 'b'​​​​.
You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
Return the minimum number of deletions needed to make s balanced.

Example 1:
Input: s = "aababbab"
Output: 2
Explanation: You can either:
Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").

Example 2:
Input: s = "bbaaaaabb"
Output: 2
Explanation: The only solution is to delete the first two characters.

Constraints:
1 <= s.length <= 10^5
s[i] is 'a' or 'b'.

hints:
1 You need to find for every index the number of Bs before it and the number of A's after it
2 You can speed up the finding of A's and B's in suffix and prefix using preprocessing

analysis:
optimized DP
TC: O(N)
"""

class MinimumDeletionsToMakeStringBalanced:
    def minimumDeletions(self, s: str) -> int:
        res = b_cnt = 0
        for c in s:
            if c == 'b':
                b_cnt += 1
            else:
                # Two cases: remove 'a' or keep 'a'
                res = min(res + 1, b_cnt)
        return res