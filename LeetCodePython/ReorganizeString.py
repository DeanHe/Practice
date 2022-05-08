"""
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""


Constraints:
1 <= s.length <= 500
s consists of lowercase English letters.

hint:
1 Alternate placing the most common letters.

analysis:
priority queue solution
"""
import collections
import heapq


class ReorganizeString:
    def reorganizeString(self, s: str) -> str:
        res, cnt = [], collections.Counter(s)
        pq = [(-v, k) for k, v in cnt.items()]
        heapq.heapify(pq)
        pre_freq, pre_letter = 0, ''
        while pq:
            freq, letter = heapq.heappop(pq)
            res.append(letter)
            if pre_freq < 0:
                heapq.heappush(pq, (pre_freq, pre_letter))
            freq += 1
            pre_freq, pre_letter = freq, letter
        res = ''.join(res)
        if len(res) != len(s):
            return ""
        return res
