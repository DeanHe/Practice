"""
You are given a string s, which is known to be a concatenation of anagrams of some string t.
Return the minimum possible length of the string t.
An anagram is a word or phrase formed by rearranging the letters of a word or phrase, typically using all the original letters exactly once.

Example 1:
Input: s = "abba"
Output: 2
Explanation:
One possible string t could be "ba".

Example 2:
Input: s = "cdef"
Output: 4
Explanation:
One possible string t could be "cdef", notice that t can be equal to s.

Constraints:
1 <= s.length <= 10^5
s consist only of lowercase English letters.

hint:
1 The answer should be a divisor of s.length.
2 Check each candidate naively.
"""
class MinimumLengthOfAnagramConcatenation:
    def minAnagramLength(self, word: str) -> int:
        sz = len(word)
        anagrams = []
        for l in range(1, sz + 1):
            if sz % l == 0:
                anagrams.append(word[0:l])
        for anagram in anagrams:
            a_len = len(anagram)
            a = sorted(anagram)
            i = 0
            while i <= sz - a_len:
                if sorted(word[i:i+a_len]) != a:
                    break
                i += a_len
            if i == sz:
                return a_len
        return -1


