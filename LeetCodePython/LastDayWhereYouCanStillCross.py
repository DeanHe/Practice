"""
There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.

Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).

You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).

Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

Example 1:
Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
Output: 2
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.

Example 2:
Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
Output: 1
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 1.

Example 3:
Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
Output: 3
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.


Constraints:
2 <= row, col <= 2 * 10^4
4 <= row * col <= 2 * 10^4
cells.length == row * col
1 <= ri <= row
1 <= ci <= col
All the values of cells are unique.

analysis:
union find by land expand to find a path connect top to bottom (reverse water flood order)
TC: O(row⋅col⋅α(row⋅col))
"""
from collections import deque
from typing import List


class LastDayWhereYouCanStillCross:
    def latestDayToCross(self, row: int, col: int, cells: List[List[int]]) -> int:
        # reserve two positions for top and bottom
        parent = list(range(row * col + 2))
        size = [1] * (row * col + 2)

        def find_root(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while parent[x] != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return root

        def union(a, b):
            root_a, root_b = find_root(a), find_root(b)
            if root_a != root_b:
                if size[root_a] < size[root_b]:
                    parent[root_a] = root_b
                    size[root_b] += size[root_a]
                    size[root_a] = 0
                else:
                    parent[root_b] = root_a
                    size[root_a] += size[root_b]
                    size[root_b] = 0

        n = len(cells)
        dirs = [0, 1, 0, -1, 0]
        grid = [[0] * col for _ in range(row)]
        for i in range(n - 1, -1, -1):
            r, c = cells[i]
            r, c = r - 1, c - 1
            # compensate with new land
            grid[r][c] = 1
            idx = r * col + c + 1
            for j in range(len(dirs) - 1):
                nb_r, nb_c = r + dirs[j], c + dirs[j + 1]
                nb_idx = nb_r * col + nb_c + 1
                if 0 <= nb_r < row and 0 <= nb_c < col and grid[nb_r][nb_c] == 1:
                    union(idx, nb_idx)
            if r == 0:
                union(0, idx)
            if r == row - 1:
                union(row * col + 1, idx)
            if find_root(0) == find_root(row * col + 1):
                return i
        return n


