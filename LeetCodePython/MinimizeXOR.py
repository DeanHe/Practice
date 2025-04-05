"""
Given two positive integers num1 and num2, find the integer x such that:

x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.

Return the integer x. The test cases are generated such that x is uniquely determined.

The number of set bits of an integer is the number of 1's in its binary representation.

Example 1:
Input: num1 = 3, num2 = 5
Output: 3
Explanation:
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.

Example 2:
Input: num1 = 1, num2 = 12
Output: 3
Explanation:
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.

Constraints:
1 <= num1, num2 <= 10^9

analysis:
TC O(N)
"""
class MinimizeXOR:
    def minimizeXor(self, num1: int, num2: int) -> int:
        res_target_bits = num2.bit_count()
        res_bits = 0
        res = 0
        # start from most significant bit
        i = 31
        # res bits are not enough
        while res_bits < res_target_bits:
            # if num1 bit i is 1 or must set all remaining bits
            if((1 << i) & num1) > 0 or res_target_bits - res_bits > i:
                # set res bit i to 1
                res |= 1 << i
                res_bits += 1
            i -= 1
        return res


