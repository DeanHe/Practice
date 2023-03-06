"""
You are given a positive integer n, you can do the following operation any number of times:

Add or subtract a power of 2 from n.
Return the minimum number of operations to make n equal to 0.

A number x is power of 2 if x == 2i where i >= 0.



Example 1:

Input: n = 39
Output: 3
Explanation: We can do the following operations:
- Add 20 = 1 to n, so now n = 40.
- Subtract 23 = 8 from n, so now n = 32.
- Subtract 25 = 32 from n, so now n = 0.
It can be shown that 3 is the minimum number of operations we need to make n equal to 0.
Example 2:

Input: n = 54
Output: 3
Explanation: We can do the following operations:
- Add 21 = 2 to n, so now n = 56.
- Add 23 = 8 to n, so now n = 64.
- Subtract 26 = 64 from n, so now n = 0.
So the minimum number of operations is 3.

Constraints:
1 <= n <= 10^5

hints:
1 Can we set/unset the bits in binary representation?
2 If there are multiple adjacent ones, how can we optimally add and subtract in 2 operations such that all ones get unset?

analysis:
binary format, pre_count
TC: O(N)
"""

class MinimumOperationsToReduceAnIntegerTo0:
    def minOperations(self, n: int) -> int:
        ops = 0
        bits = list(bin(n)[2:])
        ones = 0
        i = 0
        while i < len(bits):
            if bits[i] == '1':
                ones += 1
                i += 1
            else:
                if ones == 1:
                    ops += 1
                    i += 1
                elif ones > 1:
                    bits[i] = '1'
                    ops += 1
                else:
                    i += 1
                ones = 0
        if ones == 1:
            ops += 1
        elif ones > 1:
            ops += 2
        return ops



