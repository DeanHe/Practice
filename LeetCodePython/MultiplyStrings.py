"""
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"


Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
"""


class MultiplyStrings:
    def multiply(self, num1: str, num2: str) -> str:
        res, carry1 = 0, 1
        for n1 in num1[::-1]:
            carry2 = 1
            for n2 in num2[::-1]:
                res += int(n1) * carry1 * int(n2) * carry2
                carry2 *= 10
            carry1 *= 10
        return str(res)