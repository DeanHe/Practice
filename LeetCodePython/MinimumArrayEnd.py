"""
You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.
Return the minimum possible value of nums[n - 1].

Example 1:
Input: n = 3, x = 4
Output: 6
Explanation:
nums can be [4,5,6] and its last element is 6.

Example 2:
Input: n = 2, x = 7
Output: 15
Explanation:
nums can be [7,15] and its last element is 15.

Constraints:
1 <= n, x <= 10^8

hints:
1 Each element of the array should be obtained by “merging” x and v where v = 0, 1, 2, …(n - 1).
2 To merge x with another number v, keep the set bits of x untouched, for all the other bits, fill the set bits of v from right to left in order one by one.
3 So the final answer is the “merge” of x and n - 1.
"""

class MinimumArrayEnd:
    def minEnd(self, n: int, x: int) -> int:
        res = x
        n -= 1
        bit = 1
        while n > 0:
            if bit & res == 0:
                res |= (n & 1) * bit
                n >>= 1
            bit <<= 1
        return res


