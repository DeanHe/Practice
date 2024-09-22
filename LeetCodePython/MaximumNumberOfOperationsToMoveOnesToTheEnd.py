"""
You are given a binary string s.

You can perform the following operation on the string any number of times:

Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
Move the character s[i] to the right until it reaches the end of the string or another '1'. For example, for s = "010010", if we choose i = 1, the resulting string will be s = "000110".
Return the maximum number of operations that you can perform.

Example 1:
Input: s = "1001101"
Output: 4
Explanation:
We can perform the following operations:
Choose index i = 0. The resulting string is s = "0011101".
Choose index i = 4. The resulting string is s = "0011011".
Choose index i = 3. The resulting string is s = "0010111".
Choose index i = 2. The resulting string is s = "0001111".

Example 2:
Input: s = "00111"
Output: 0

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.

hints:
1 It is optimal to perform the operation on the lowest index possible each time.
2 Traverse the string from left to right and perform the operation every time it is possible.
"""
class MaximumNumberOfOperationsToMoveOnesToTheEnd:
    def maxOperations(self, s: str) -> int:
        sz = len(s)
        res = 0
        ones = 0
        i = 0
        while i < sz:
            if s[i] == '1':
                ones += 1
                i += 1
            else:
                while i < sz and s[i] == '0':
                    i += 1
                res += ones
        return res
