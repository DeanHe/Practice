"""
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
"""
from typing import List


class WordBreak:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        max_len, s_len = 0, len(s)
        for word in wordDict:
            max_len = max(max_len, len(word))
        dp = [False] * (s_len + 1)
        dp[0] = True
        for i in range(1, s_len + 1):
            for j in range(i - 1, max(-1, i - max_len - 1), -1):
                if dp[j]:
                    if s[j:i] in wordDict:
                        dp[i] = True
                        break
        return dp[-1]