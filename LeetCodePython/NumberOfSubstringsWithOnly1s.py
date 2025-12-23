"""
Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.

Example 1:
Input: s = "0110111"
Output: 9
Explanation: There are 9 substring in total with only 1's characters.
"1" -> 5 times.
"11" -> 3 times.
"111" -> 1 time.

Example 2:
Input: s = "101"
Output: 2
Explanation: Substring "1" is shown 2 times in s.

Example 3:
Input: s = "111111"
Output: 21
Explanation: Each substring contains only 1's characters.

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.

hints:
1 Count number of 1s in each consecutive-1 group. For a group with n consecutive 1s, the total contribution of it to the final answer is (n + 1) * n // 2.

analysis:
TC: O(N)
"""

class NumberOfSubstringsWithOnly1s:
    def numSub(self, s: str) -> int:
        res = 0
        MOD = 10 ** 9 + 7
        ones = 0
        for c in s:
            if c == '1':
                res = (res + ones + 1) % MOD
                ones += 1
            else:
                ones = 0
        return res