"""
You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

Example 1:
Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation:
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.

Example 2:
Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.


Constraints:
1 <= s.length <= 10^5
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length

analysis:
sliding window

Instead of taking k of each character from left and right, we take at most count(c) - k of each character from middle.
the problem becomes "finding the longest substring where the occurrence of each character is within limits"

TC: O(N)
SC: O(1)
"""
class TakeKofEachCharacterFromLeftAndRight:
    def takeCharacters(self, s: str, k: int) -> int:
        n = len(s)
        res = l = 0
        upper_limits = {c: s.count(c) - k for c in 'abc'}
        if any(x < 0 for x in upper_limits.values()):
            return -1
        cnts = {c: 0 for c in 'abc'}
        for r, c in enumerate(s):
            cnts[c] += 1
            while cnts[c] > upper_limits[c]:
                cnts[s[l]] -= 1
                l += 1
            res = max(res, r - l + 1)
        return n - res
