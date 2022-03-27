"""
You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.



Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 10^5

hint:
1 You need to maintain only the biggest 3 distinct sums
2 The limits are small enough for you to iterate over all rhombus sizes then iterate over all possible borders to get the sums

analysis:
TC O(rows * cols * min(rows, cols))
SC O(rows * cols)
"""
import heapq
from functools import lru_cache
from typing import List


class GetBiggestThreeRhombusSumsInaGrid:
    def getBiggestThree(self, grid: List[List[int]]) -> List[int]:
        rows, cols, heap = len(grid), len(grid[0]), []

        def update(heap, n):
            if n not in heap:
                heapq.heappush(heap, n)
                if len(heap) > 3:
                    heapq.heappop(heap)
            return heap

        for r in range(rows):
            for c in range(cols):
                update(heap, grid[r][c])

        @lru_cache(None)
        def dp(c, r, dir):
            if not 0 <= r < rows and not 0 <= c < cols:
                return 0
            return dp(c - 1, r + dir, dir) + grid[r][c]

        for q in range(1, (1 + min(rows, cols)) // 2):
            for c in range(q, cols - q):
                for r in range(q, rows - q):
                    p1 = dp(c + q, r, - 1) - dp(c, r - q, -1)
                    p2 = dp(c - 1, r + q - 1, -1) - dp(c - q - 1, r - 1, -1)
                    p3 = dp(c, r - q, 1) - dp(c - q, r, 1)
                    p4 = dp(c + q - 1, r + 1, 1) - dp(c - 1, r + q + 1, 1)
                    update(heap, p1 + p2 + p3 + p4)

        return sorted(heap[::-1])