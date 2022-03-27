"""
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False


Constraints:
1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.

tag:
similar to 'find all Anagrams'
"""
import collections


class PermutationInString:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        cnt1, s, e = collections.Counter(s1), 0, 0
        size1 = len(cnt1)
        while e < len(s2):
            ec = s2[e]
            if ec in cnt1:
                cnt1[ec] -= 1
                if cnt1[ec] == 0:
                    size1 -= 1
            e += 1
            while size1 == 0:
                if e - s == len(s1):
                    return True
                sc = s2[s]
                if sc in cnt1:
                    cnt1[sc] += 1
                    if cnt1[sc] == 1:
                        size1 += 1
                s += 1
        return False
