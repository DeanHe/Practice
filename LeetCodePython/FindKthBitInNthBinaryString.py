"""
Given two positive integers n and k, the binary string Sn is formed as follows:

S1 = "0"
Si = S(i - 1) + "1" + reverse(invert(Si - 1)) for i > 1
Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).

For example, the first four strings in the above sequence are:
S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
Return the kth bit in Sn. It is guaranteed that k is valid for the given n.

Example 1:
Input: n = 3, k = 1
Output: "0"
Explanation: S3 is "0111001".
The 1st bit is "0".

Example 2:
Input: n = 4, k = 11
Output: "1"
Explanation: S4 is "011100110110001".
The 11th bit is "1".

Constraints:
1 <= n <= 20
1 <= k <= 2^n - 1

hints:
1 Since n is small, we can simply simulate the process of constructing S1 to Sn.

analysis:
recursion TC:O(N)
"""

class Solution:
    def findKthBit(self, n: int, k: int) -> str:
        if n == 1:
            return '0'
        size = 1 << n
        if k == size // 2:
            return '1'
        elif k < size // 2:
            return self.findKthBit(n - 1, k)
        else:
            corresponding_bit = self.findKthBit(n - 1, size - k)
            return '1' if corresponding_bit == '0' else '1'
