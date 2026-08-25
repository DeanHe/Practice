"""
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Example 1:
Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".

Example 2:
Input: s = "010"
Output: 0
Explanation: The string is already alternating.

Example 3:
Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.

hints:
1 Note what actually matters is how many 0s and 1s are in odd and even positions
2 For every cyclic shift we need to count how many 0s and 1s are at each parity and convert the minimum between them for each parity

analysis:
Sliding window
TC:O(N)
"""
import math


class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating:
    def minFlips(self, s: str) -> int:
        sz = len(s)
        res = math.inf
        res1 = res0 = 0
        s += s
        s_start1 = s_start0 = ''
        for i in range(2 * sz):
            s_start1 += '1' if i % 2 == 0 else '0'
            s_start0 += '0' if i % 2 == 0 else '1'
        for i in range(2 * sz):
            if s_start1[i] != s[i]:
                res1 += 1
            if s_start0[i] != s[i]:
                res0 += 1
            if i >= sz:
                # sliding window remove head
                if s_start1[i - sz] != s[i - sz]:
                    res1 -= 1
                if s_start0[i - sz] != s[i - sz]:
                    res0 -= 1
                if i >= sz - 1:
                    res = min(res, res1, res0)
        return res