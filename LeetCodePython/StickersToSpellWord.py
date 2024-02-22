"""
We are given n different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.

Example 1:
Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.

Example 2:
Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.

Constraints:
n == stickers.length
1 <= n <= 50
1 <= stickers[i].length <= 10
1 <= target.length <= 15
stickers[i] and target consist of lowercase English letters.

hints:
1 We want to perform an exhaustive search, but we need to speed it up based on the input data being random.
For all stickers, we can ignore any letters that are not in the target word. When our candidate answer won't be smaller than an answer we have already found, we can stop searching this path. When a sticker dominates another, we shouldn't include the dominated sticker in our sticker collection. [Here, we say a sticker `A` dominates `B` if `A.count(letter) >= B.count(letter)` for all letters.]

analysis:
dfs + memorization
TC: O(len(target) * len(stickers) * 26 ??) exponential
"""
from typing import List


class StickersToSpellWord:
    def minStickers(self, stickers: List[str], target: str) -> int:
        MAX = 10 ** 9
        stickers_len = len(stickers)
        stickers_mp = [[0] * 26 for _ in range(stickers_len)]
        for i, sticker in enumerate(stickers):
            for c in sticker:
                stickers_mp[i][ord(c) - ord('a')] += 1
        dp = {'': 0}

        def dfs(remain):
            if remain in dp:
                return dp[remain]
            res = MAX
            remain_mp = [0] * 26
            for c in remain:
                remain_mp[ord(c) - ord('a')] += 1
            for i in range(stickers_len):
                if stickers_mp[i][ord(remain[0]) - ord('a')] == 0:
                    continue
                tmp = ''
                # add remaining characters after using stickers[i]
                for j in range(26):
                    if stickers_mp[i][j] < remain_mp[j]:
                        tmp += chr(ord('a') + j) * (remain_mp[j] - stickers_mp[i][j])
                ret = dfs(tmp)
                if ret != -1:
                    res = min(res, ret + 1)
            dp[remain] = res if res != MAX else -1
            return dp[remain]


        return dfs(target)

