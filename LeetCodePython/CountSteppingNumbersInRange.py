"""
Given two positive integers low and high represented as strings, find the count of stepping numbers in the inclusive range [low, high].

A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.

Return an integer denoting the count of stepping numbers in the inclusive range [low, high].

Since the answer may be very large, return it modulo 10^9 + 7.

Note: A stepping number should not have a leading zero.

Example 1:
Input: low = "1", high = "11"
Output: 10
Explanation: The stepping numbers in the range [1,11] are 1, 2, 3, 4, 5, 6, 7, 8, 9 and 10. There are a total of 10 stepping numbers in the range. Hence, the output is 10.

Example 2:
Input: low = "90", high = "101"
Output: 2
Explanation: The stepping numbers in the range [90,101] are 98 and 101. There are a total of 2 stepping numbers in the range. Hence, the output is 2.

Constraints:
1 <= int(low) <= int(high) < 10100
1 <= low.length, high.length <= 100
low and high consist of only digits.
low and high don't have any leading zeros.

hints:
1 Calculate the number of stepping numbers in the range [1, high] and subtract the number of stepping numbers in the range [1, low - 1].
2 The main problem is calculating the number of stepping numbers in the range [1, x].
3 First, calculate the number of stepping numbers shorter than x in length, which can be done using dynamic programming. (dp[i][j] is the number of i-digit stepping numbers ending with digit j).
4 Finally, calculate the number of stepping numbers that have the same length as x similarly. However, this time we need to maintain whether the prefix (in string) is smaller than or equal to x in the DP state.

analysis:
Using Digit DP to count stepping numbers from [0...n]
Then the result is count(high) - count(low-1)

TC: O(N)
"""
from functools import cache


class CountSteppingNumbersInRange:
    def countSteppingNumbers(self, low: str, high: str) -> int:
        MOD = 10**9 + 7

        # count number of stepping numbers in range [0...n]
        def count(num):
            @cache
            def dfs(i, bounded, last_digit, leading_zero):
                if i == len(num):
                    return 1
                res = 0
                max_digit = int(num[i]) if bounded else 9
                for d in range(0, max_digit + 1):
                    next_bounded = bounded and d == max_digit
                    next_leading_zero = leading_zero and d == 0
                    if next_leading_zero:
                        # for leading zero, we shouldn't treat lastDigit=d
                        res = (res + dfs(i + 1, next_bounded, last_digit, next_leading_zero)) % MOD
                    elif last_digit == -1 or abs(d - last_digit) == 1:
                        res = (res + dfs(i + 1, next_bounded, d, next_leading_zero)) % MOD
                return res
            return dfs(0, True, -1, True) + 1

        if low == "0":
            return count(high)
        low_minus_one = str(int(low) - 1)
        return (count(high) - count(low_minus_one) + MOD) % MOD

