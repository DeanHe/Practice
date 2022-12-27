"""
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0

Constraints:
1 <= prices.length <= 5000
0 <= prices[i] <= 1000
"""
from typing import List


class BestTimeToBuyAndSellStockWithCooldown:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        bought, sold, rest = [0] * (n + 1), [0] * (n + 1), [0] * (n + 1)
        bought[0] = float('-inf')
        for i in range(n):
            bought[i + 1] = max(bought[i], rest[i] - prices[i])
            sold[i + 1] = max(sold[i], bought[i] + prices[i])
            rest[i + 1] = max(rest[i], bought[i], sold[i])
        return max(sold[-1], rest[-1])