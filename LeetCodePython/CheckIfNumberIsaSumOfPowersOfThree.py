"""
Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.

Example 1:
Input: n = 12
Output: true
Explanation: 12 = 31 + 32

Example 2:
Input: n = 91
Output: true
Explanation: 91 = 30 + 32 + 34

Example 3:
Input: n = 21
Output: false

Constraints:
1 <= n <= 10^7

hints:
1 Let's note that the maximum power of 3 you'll use in your solution is 3^16
2 The number can not be represented as a sum of powers of 3 if it's ternary presentation has a 2 in it

Analysis:
Math
TC: O(log3^N)
"""

class CheckIfNumberIsaSumOfPowersOfThree:
    def checkPowersOfThree(self, n: int) -> bool:
        power = 0
        while 3 ** power <= n:
            power += 1
        while n > 0:
            if 3 ** power <= n:
                n -= 3 ** power
            if 3 ** power <= n:
                return False
            power -= 1
        return True