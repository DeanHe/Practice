"""
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.



Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Example 2:

Input: heights = [[2,1],[1,2]]
Output: [[0,0],[0,1],[1,0],[1,1]]


Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105
"""
import collections


class PacificAtlanticWaterFlow(object):
    def pacificAtlantic(self, heights):
        """
        :type heights: List[List[int]]
        :rtype: List[List[int]]
        """
        rows, cols = len(heights), len(heights[0])
        pacific_queue = collections.deque()
        atlantic_queue = collections.deque()
        for r in range(rows):
            pacific_queue.append((r, 0))
        for c in range(1, cols):
            pacific_queue.append((0, c))
        for r in range(rows):
            atlantic_queue.append((r, cols - 1))
        for c in range(cols - 1):
            atlantic_queue.append((rows - 1, c))

        def bfs(q):
            visited = set(q)
            while q:
                (r, c) = q.popleft()
                for (dr, dc) in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                    if 0 <= r + dr < rows \
                            and 0 <= c + dc < cols \
                            and (r + dr, c + dc) not in visited \
                            and heights[r][c] <= heights[r + dr][c + dc]:
                        q.append((r + dr, c + dc))
                        visited.add((r + dr, c + dc))
            return visited

        return list(bfs(pacific_queue).intersection(bfs(atlantic_queue)))

    def pacificAtlanticDFS(self, heights):
        """
        :type heights: List[List[int]]
        :rtype: List[List[int]]
        """
        if not heights:
            return []
        dirs = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        rows, cols = len(heights), len(heights[0])
        pacific_visited = [[False for _ in range(cols)] for _ in range(rows)]
        atlantic_visited = [[False for _ in range(cols)] for _ in range(rows)]
        res = []

        def dfs(r, c, visited):
            visited[r][c] = True
            for dir in dirs:
                nb_r, nb_c = r + dir[0], c + dir[1]
                if 0 <= nb_r < rows \
                        and 0 <= nb_c < cols \
                        and not visited[nb_r][nb_c] \
                        and heights[r][c] <= heights[nb_r][nb_c]:
                    dfs(nb_r, nb_c, visited)

        for r in range(rows):
            dfs(r, 0, pacific_visited)
            dfs(r, cols - 1, atlantic_visited)
        for c in range(cols):
            dfs(0, c, pacific_visited)
            dfs(rows - 1, c, atlantic_visited)

        for r in range(rows):
            for c in range(cols):
                if pacific_visited[r][c] and atlantic_visited[r][c]:
                    res.append((r, c))
        return res