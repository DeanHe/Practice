"""
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
"""
import collections
from typing import List


class WordBreakII:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        break_map = {}

        def dfs(word):
            if word in break_map:
                return break_map[word]
            res = []
            if len(word) == 0:
                res.append("")
                return res
            for i in range(1, len(word) + 1):
                left, right = word[:i], word[i:]
                if left in wordDict:
                    right_combination = dfs(right)
                    for comb in right_combination:
                        if len(comb) > 0:
                            res.append(left + " " + comb)
                        else:
                            res.append(left)
            break_map[word] = res
            return res

        return dfs(s)
