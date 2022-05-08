"""
Alice plays the following game, loosely based on the card game "21".
Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer.
Each draw is independent and the outcomes have equal probabilities.
Alice stops drawing numbers when she gets k or more points.
Return the probability that Alice has n or fewer points.
Answers within 10-5 of the actual answer are considered accepted.

Example 1:
Input: n = 10, k = 1, maxPts = 10
Output: 1.00000
Explanation: Alice gets a single card, then stops.

Example 2:
Input: n = 6, k = 1, maxPts = 10
Output: 0.60000
Explanation: Alice gets a single card, then stops.
In 6 out of 10 possibilities, she is at or below 6 points.

Example 3:
Input: n = 21, k = 17, maxPts = 10
Output: 0.73278

Constraints:
0 <= k <= n <= 10^4
1 <= maxPts <= 10^4

analysis:
dp[i] means the probability to get i points
logic:
probability to get i points = sum(probability to get i - x points * 1 / maxPts) for x can be any of [1, maxPts]
uses pre_sum_i_w to represent
where 0 <= i - w < K
"""

class New21Game:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        if k == 0 or n >= k + maxPts:
            return 1
        dp = [0] * (n + 1)
        pre_sum_x, res = 1, 0
        dp[0] = 1
        for i in range(1, n + 1):
            dp[i] = pre_sum_x / maxPts
            if i < k:
                pre_sum_x += dp[i]
            else:
                res += dp[i]
            if i - maxPts >= 0:
                pre_sum_x -= dp[i - maxPts]
        return res
