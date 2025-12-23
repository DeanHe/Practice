"""
You are given an m x n grid where each cell contains one of the values 0, 1, or 2. You are also given an integer k.

You start from the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1, n - 1) by moving only right or down.

Each cell contributes a specific score and incurs an associated cost, according to their cell values:

0: adds 0 to your score and costs 0.
1: adds 1 to your score and costs 1.
2: adds 2 to your score and costs 1.
Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.

Note: If you reach the last cell but the total cost exceeds k, the path is invalid.

Example 1:
Input: grid = [[0, 1],[2, 0]], k = 1
Output: 2

Explanation:
The optimal path is:
Cell	grid[i][j]	Score	Total
Score	Cost	Total
Cost
(0, 0)	0	0	0	0	0
(1, 0)	2	2	2	1	1
(1, 1)	0	0	2	0	1
Thus, the maximum possible score is 2.

Example 2:
Input: grid = [[0, 1],[1, 2]], k = 1
Output: -1

Explanation:
There is no path that reaches cell (1, 1) without exceeding cost k. Thus, the answer is -1.

Constraints:
1 <= m, n <= 200
0 <= k <= 10^3
grid[0][0] == 0
0 <= grid[i][j] <= 2

hints:
1 Use dynamic programming.
2 Let dp[i][j][c] = max score at cell (i,j) with total cost exactly c (0 <= c <= k).
3 Update dp[i][j][c] from (i-1,j) and (i,j-1) using cost = (grid[i][j] == 0 ? 0 : 1) and score = grid[i][j].
4 Answer = max(dp[m-1][n-1][c]) for c=0..k, or -1 if none.
"""
from collections import defaultdict
from typing import List


class MaximumPathScoreInaGrid:
    def maxPathScore(self, grid: List[List[int]], k: int) -> int:
        res = -1
        rows, cols = len(grid), len(grid[0])
        dp = [[defaultdict(int) for _ in range(cols)] for _ in range(rows)]
        cur_cost = 1 if grid[0][0] > 0 else 0
        dp[0][0][cur_cost] = grid[0][0]
        for r in range(rows):
            for c in range(cols):
                if r > 0:
                    for cost, most_score in dp[r - 1][c].items():
                        cur_cost = 1 if grid[r][c] > 0 else 0
                        if cost + cur_cost <= k:
                            dp[r][c][cost + cur_cost] = max(dp[r][c][cost + cur_cost], most_score + grid[r][c])
                if c > 0:
                    for cost, most_score in dp[r][c - 1].items():
                        cur_cost = 1 if grid[r][c] > 0 else 0
                        if cost + cur_cost <= k:
                            dp[r][c][cost + cur_cost] = max(dp[r][c][cost + cur_cost], most_score + grid[r][c])
        if dp[-1][-1]:
            res = max(dp[-1][-1].values())
        return res
