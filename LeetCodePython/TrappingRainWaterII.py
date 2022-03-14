"""
Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.



Example 1:


Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
Example 2:


Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10


Constraints:

m == heightMap.length
n == heightMap[i].length
1 <= m, n <= 200
0 <= heightMap[i][j] <= 2 * 104
"""
import heapq


class TrappingRainWaterII(object):
    def trapRainWater(self, heightMap):
        """
        :type heightMap: List[List[int]]
        :rtype: int
        """
        if not heightMap or not heightMap[0]:
            return 0
        res, rows, cols = 0, len(heightMap), len(heightMap[0])
        visited = [[0] * cols for _ in range(rows)]
        heap = []
        for r in range(rows):
            for c in range(cols):
                if r == 0 or c == 0 or r == rows - 1 or c == cols - 1:
                    heapq.heappush(heap, (heightMap[r][c], r, c))
                    visited[r][c] = 1
        while heap:
            height, r, c = heapq.heappop(heap)
            for nb_r, nb_c in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
                if 0 <= nb_r < rows and 0 <= nb_c < cols and not visited[nb_r][nb_c]:
                    res += max(0, height - heightMap[nb_r][nb_c])
                    heapq.heappush(heap, (max(height, heightMap[nb_r][nb_c]), nb_r, nb_c))
                    visited[nb_r][nb_c] = 1
        return res