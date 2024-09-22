"""
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

Example 1:
Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

Example 2:
Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.

Example 3:
Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.

Constraints:
1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.

hints:
1 Represent the counts (odd or even) of vowels with a bitmask.
2 Precompute the prefix xor for the bitmask of vowels and then get the longest valid substring.

Analysis:
prefix XOR
TC: O(N)
"""

class FindTheLongestSubstringContainingVowelsInEvenCounts:
    def findTheLongestSubstring(self, s: str) -> int:
        res = 0
        prefix_xor = 0
        char_bitmask = {'a': 1, 'e': 2, 'i': 4, 'o': 8, 'u': 16}
        state_to_idx = {0:-1}
        for i, c in enumerate(s):
            if c in char_bitmask:
                prefix_xor ^= char_bitmask[c]
            if prefix_xor in state_to_idx:
                res = max(res, i - state_to_idx[prefix_xor])
            else:
                state_to_idx[prefix_xor] = i
        return res



