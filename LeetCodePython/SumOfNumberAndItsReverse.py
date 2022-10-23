"""
Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer and its reverse, or false otherwise.

Example 1:
Input: num = 443
Output: true
Explanation: 172 + 271 = 443 so we return true.

Example 2:
Input: num = 63
Output: false
Explanation: 63 cannot be expressed as the sum of a non-negative integer and its reverse so we return false.

Example 3:
Input: num = 181
Output: true
Explanation: 140 + 041 = 181 so we return true. Note that when a number is reversed, there may be leading zeros.

Constraints:
0 <= num <= 10^5

hints:
1 The constraints are small enough that we can check every number.
2 To reverse a number, first convert it to a string. Then, create a new string that is the reverse of the first one. Finally, convert the new string back into a number.
"""

class SumOfNumberAndItsReverse:
    def sumOfNumberAndReverse(self, num: int) -> bool:
        for n in range(0, num + 1):
            n_str = str(n)
            rev_str = n_str[::-1]
            if int(n_str) + int(rev_str) == num:
                return True
        return False