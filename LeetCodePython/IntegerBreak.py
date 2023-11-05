"""
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

Example 1:
Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

Constraints:
2 <= n <= 58

hints:
1 There is a simple O(n) solution to this problem.
2 You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

TC: O(N)
Interestingly, it is optimal to only split n into 2 and 3.
The inequality of arithmetic and geometric means shows that to maximize the product of a set of numbers with a fixed sum,
the numbers should all be equal. This means that we should split n into a copies of x
"""

class IntegerBreak:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
        res = 1
        while n > 4:
            res *= 3
            n -= 3
        res *= n
        return res
