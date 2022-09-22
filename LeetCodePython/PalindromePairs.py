"""
Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

Example 1:
Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
Example 3:

Input: words = ["a",""]
Output: [[0,1],[1,0]]


Constraints:
1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lower-case English letters.

analysis:
TC O(n * m)  where n is the length of words and m is the length of wordlist.
"""
from typing import List


class PalindromePairs:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        def is_palindrome(word):
            return word == word[::-1]
        word_idx = {w: i for i, w in enumerate(words)}
        res = []
        for i, w in enumerate(words):
            for j in range(len(w) + 1):
                pre, post = w[:j], w[j:]
                pre_rev, post_rev = pre[::-1], post[::-1]
                if is_palindrome(pre) and post_rev != w and post_rev in word_idx:
                    res.append([word_idx[post_rev], i])
                if j != len(w) and is_palindrome(post) and pre_rev != w and pre_rev in word_idx:
                    res.append([i, word_idx[pre_rev]])
        return res