"""
Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.

Return the length of n. If there is no such n, return -1.

Note: n may not fit in a 64-bit signed integer.

Example 1:
Input: k = 1
Output: 1
Explanation: The smallest answer is n = 1, which has length 1.

Example 2:
Input: k = 2
Output: -1
Explanation: There is no such positive integer n divisible by 2.

Example 3:
Input: k = 3
Output: 3
Explanation: The smallest answer is n = 111, which has length 3.

Constraints:
1 <= k <= 10^5

hints:
1 11111 = 1111 * 10 + 1 We only need to store remainders modulo K.
2 If we never get a remainder of 0, why would that happen, and how would we know that?

Analysis:
Furthermore, we can improve this algorithm with Pigeonhole Principle.
Recall that the number of possible values of remainder (ranging from 0 to K-1) is limited, and in fact, the number is K.
As a result, if the while-loop continues more than K times, and haven't stopped,
then we can conclude that remainder repeats -- you can not have more than K different remainder.

TC: O(K)
"""

class SmallestIntegerDivisibleByK:
    def smallestRepunitDivByK(self, k: int) -> int:
        res = cur = 1
        for _ in range(k):
            if cur % k == 0:
                return res
            res += 1
            cur = cur * 10 + 1
        return -1