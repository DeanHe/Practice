"""
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Constraints:
0 <= k <= 100
0 <= prices.length <= 1000
0 <= prices[i] <= 1000

Challenge
O(nk) time.

analysis:
dp: mem(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j< len).
"""
from typing import List


class BestTimeToBuyAndSellStockIV:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        if not prices:
            return 0

        def sell_stock_II():
            res = 0
            for i in range(1, len(prices)):
                if prices[i - 1] < prices[i]:
                    res += prices[i] - prices[i - 1]
            return res

        if k >= len(prices) // 2:
            return sell_stock_II()

        dp = [[0] * len(prices) for _ in range(k + 1)]
        for i in range(1, k + 1):
            hold = -prices[0]
            for j in range(1, len(prices)):
                dp[i][j] = max(dp[i][j - 1], hold + prices[j])
                # accumulate means the maximum profit of just doing at most i-1 transactions,
                # using at most first j-1 prices, and buying the stock at price[j]
                hold = max(hold, dp[i - 1][j - 1] - prices[j])
        return dp[k][-1]