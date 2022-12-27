"""
You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queres[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.

Example 1:
Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.

Example 2:
Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
k == queries.length
1 <= k <= 10^4
1 <= grid[i][j], queries[i] <= 10^6

analysis:
TC: O(rows * cols * log(rows * cols))
SC: O(rows * cols)
"""
import heapq
from typing import List


class MaximumNumberOfPointsFromGridQueries:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        rows, cols = len(grid), len(grid[0])
        dirs = [0, 1, 0, -1, 0]
        pq = [(grid[0][0], 0, 0)]
        visited = set()
        visited.add((0, 0))
        order = []
        res = []
        while pq:
            val, r, c = heapq.heappop(pq)
            order.append(val)
            for i in range(len(dirs) - 1):
                nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                if 0 <= nb_r < rows and 0 <= nb_c < cols and (nb_r, nb_c) not in visited:
                    visited.add((nb_r, nb_c))
                    heapq.heappush(pq, (grid[nb_r][nb_c], nb_r, nb_c))
        # make order list monotonic increasing
        most = float('-inf')
        for i in range(len(order)):
            most = max(most, order[i])
            order[i] = most

        def binary_search(target):
            s, e = 0, len(order)
            while s + 1 < e:
                mid = (s + e) // 2
                if target <= order[mid]:
                    e = mid
                else:
                    s = mid
            if target <= order[s]:
                return s
            return e

        for q in queries:
            res.append(binary_search(q))
        return res


