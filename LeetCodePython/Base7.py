"""
Given an integer num, return a string of its base 7 representation.

Example 1:
Input: num = 100
Output: "202"
Example 2:

Input: num = -7
Output: "-10"


Constraints:

-10^7 <= num <= 10^7
"""
class Base7:
    def convertToBase7(self, num: int) -> str:
        if num == 0:
            return "0"
        n, res = abs(num), ""
        while n:
            res = n % 7 + res
            n = n // 7
        return res if num > 0 else "-" + res
