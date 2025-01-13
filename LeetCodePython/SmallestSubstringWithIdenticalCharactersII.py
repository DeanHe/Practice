"""
ou are given a binary string s of length n and an integer numOps.

You are allowed to perform the following operation on s at most numOps times:

Select any index i (where 0 <= i < n) and flip s[i]. If s[i] == '1', change s[i] to '0' and vice versa.
You need to minimize the length of the longest substring of s such that all the characters in the substring are identical.

Return the minimum length after the operations.

A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: s = "000001", numOps = 1
Output: 2
Explanation:
By changing s[2] to '1', s becomes "001001". The longest substrings with identical characters are s[0..1] and s[3..4].

Example 2:
Input: s = "0000", numOps = 2
Output: 1
Explanation:
By changing s[0] and s[2] to '1', s becomes "1010".

Example 3:
Input: s = "0101", numOps = 0
Output: 1

Constraints:
1 <= n == s.length <= 10^5
s consists only of '0' and '1'.
0 <= numOps <= n

hints:
1 Binary search for the answer.
2 Group the same digits by size of (mid + 1) and ignore any remainder. Flip one in each group (the last one).
3 For the last group, we can flip the 2nd last one.
4 What if the answer was 1?

TC: O(NlogN)
"""

class SmallestSubstringWithIdenticalCharactersII:
    def minLength(self, s: str, numOps: int) -> int:
        def interleave(start_char):
            ops = 0
            for c in s:
                if c != start_char:
                    ops += 1
                start_char = '1' if start_char == '0' else '0'
            return ops <= numOps

        def validate(length):
            if length == 1:
                return interleave('1') or interleave('0')
            ops = 0
            cnt = 0
            pre = None
            for c in s:
                if c == pre:
                    cnt += 1
                else:
                    ops += cnt // (length + 1)
                    pre = c
                    cnt = 1
            if length < cnt:
                ops += cnt // (length + 1)
            return ops <= numOps

        l, r = 1, len(s)
        while l + 1 < r:
            mid = (l + r) // 2
            if validate(mid):
                r = mid
            else:
                l = mid
        if validate(l):
            return l
        return r