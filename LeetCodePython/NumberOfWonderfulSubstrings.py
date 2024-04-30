"""
A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.

Example 1:
Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"

Example 2:
Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"

Example 3:
Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"


Constraints:
1 <= word.length <= 10^5
word consists of lowercase English letters from 'a' to 'j'.

hints:
1 For each prefix of the string, check which characters are of even frequency and which are not and represent it by a bitmask.
2 Find the other prefixes whose masks differs from the current prefix mask by at most one bit.

analysis:
prefix xor
number of subarray with at most 1 bit diff = number of subarray with 1 bit diff + number of subarray with 0 bit diff
                                           =  number of prefix xor with 1 bit diff + number of same prefix xor
"""
from collections import defaultdict


class NumberOfWonderfulSubstrings:
    def wonderfulSubstrings(self, word: str) -> int:
        res = 0
        xor_cnt = defaultdict(int)
        cur = 0
        xor_cnt[cur] = 1
        size = ord('j') - ord('a') + 1
        for c in word:
            cur ^= 1 << (ord(c) - ord('a'))
            for i in range(size):
                res += xor_cnt[cur ^ (1 << i)]
            res += xor_cnt[cur]
            xor_cnt[cur] += 1
        return res

