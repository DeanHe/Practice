"""
Given two binary strings a and b, return their sum as a binary string.

Example 1:
Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"


Constraints:
1 <= a.length, b.length <= 10^4
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
"""


class AddBinary:
    def addBinary(self, a: str, b: str) -> str:
        res, cur, carry, i, j = "", 0, 0, len(a) - 1, len(b) - 1
        while i >= 0 or j >= 0 or carry > 0:
            cur = carry
            if i >= 0:
                cur += int(a[i])
                i -= 1
            if j >= 0:
                cur += int(b[j])
                j -= 1
            res = str(cur % 2) + res
            carry = cur // 2
        return res
