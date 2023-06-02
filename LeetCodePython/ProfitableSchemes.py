"""
There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.

Example 1:
Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.

Example 2:
Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).


Constraints:
1 <= n <= 100
0 <= minProfit <= 100
1 <= group.length <= 100
1 <= group[i] <= 100
profit.length == group.length
0 <= profit[i] <= 100
"""
from typing import List


class ProfitableSchemes:
    def profitableSchemes(self, n: int, minProfit: int, group: List[int], profit: List[int]) -> int:
        MOD = 10 ** 9 + 7
        profit_len = len(profit)
        # dp[k][i][j] means: # of schemes to achieve at least i profit using j people by committing first k crimes
        dp = [[[0] * (n + 1) for _ in range(minProfit + 1)] for _ in range(profit_len + 1)]
        dp[0][0][0] = 1
        for k in range(1, profit_len + 1):
            gain = profit[k - 1]
            gang = group[k - 1]
            for i in range(minProfit + 1):
                for j in range(n + 1):
                    dp[k][i][j] = dp[k - 1][i][j]
                    if gang <= j:
                        if gain <= i:
                            dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - gain][j - gang]) % MOD
                        else:
                            dp[k][i][j] = (dp[k][i][j] + dp[k - 1][0][j - gang]) % MOD
        res = 0
        for x in dp[profit_len][minProfit]:
            res = (res + x) % MOD
        return res

