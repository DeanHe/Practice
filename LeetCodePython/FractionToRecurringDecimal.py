"""
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 4, denominator = 333
Output: "0.(012)"

Constraints:
-2^31 <= numerator, denominator <= 2^31 - 1
denominator != 0

hints:
1 No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
2 Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
3 Notice that once the remainder starts repeating, so does the divided result.
4 Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
"""

class FractionToRecurringDecimal:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        res = []
        if (numerator > 0 > denominator) or (numerator < 0 < denominator):
            res.append('-')
        numerator = abs(numerator)
        denominator = abs(denominator)
        digit = numerator // denominator
        res.append(str(digit))
        numerator = (numerator % denominator) * 10
        if numerator != 0:
            res.append('.')
        cache = {}
        while numerator:
            digit = numerator // denominator
            if numerator not in cache:
                cache[numerator] = len(res)
                res.append(str(digit))
                numerator = (numerator % denominator) * 10
            else:
                res.insert(cache[numerator], '(')
                res.append(')')
                break
        return ''.join(res)
