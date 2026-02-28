"""
Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.

Example 1:
Input: n = 1
Output: 1
Explanation: "1" in binary corresponds to the decimal value 1.

Example 2:
Input: n = 3
Output: 27
Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
After concatenating them, we have "11011", which corresponds to the decimal value 27.

Example 3:
Input: n = 12
Output: 505379714
Explanation: The concatenation results in "1101110010111011110001001101010111100".
The decimal value of that is 118505380540.
After modulo 109 + 7, the result is 505379714.

Constraints:
1 <= n <= 10^5

hints:
1 Express the nth number value in a recursion formula and think about how we can do a fast evaluation.
"""

class ConcatenationOfConsecutiveBinaryNumbers:
    def concatenatedBinary(self, n: int) -> int:
        res = 0
        MOD = 10 ** 9 + 7
        for d in range(1, n + 1):
            shift = len(bin(d)) - 2
            res = (res * (1 << shift) + d) % MOD
        return res