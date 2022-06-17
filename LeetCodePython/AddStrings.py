"""
Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.



Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"


Constraints:

1 <= num1.length, num2.length <= 104
num1 and num2 consist of only digits.
num1 and num2 don't have any leading zeros except for the zero itself.
"""

class AddStrings(object):
    def addStrings(self, num1, num2):

        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        num1, num2 = list(num1), list(num2)
        carry, res = 0, []
        while num1 or num2:
            n1 = n2 = 0
            if num1:
                n1 = int(num1.pop())
            if num2:
                n2 = int(num2.pop())
            tmp = n1 + n2 + carry
            res.append(tmp % 10)
            carry = tmp // 10
        if carry:
            res.append(carry)
        return ''.join(str(d) for d in res)[::-1]
