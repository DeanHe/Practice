"""
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.

Example 1:
Input: num = 16
Output: true

Example 2:
Input: num = 14
Output: false

Constraints:
1 <= num <= 2^31 - 1
"""

class ValidPerfectSquare:
    def isPerfectSquare(self, num: int) -> bool:
        s, e = 1, num
        while s <= e:
            mid = s + (e - s) // 2
            if mid * mid == num:
                return True
            elif mid * mid < num:
                s = mid + 1
            else:
                e = mid - 1
        return False

