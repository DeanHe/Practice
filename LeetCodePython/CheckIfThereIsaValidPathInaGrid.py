"""
You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:

1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.

Example 1:
Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).

Example 2:
Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)

Example 3:
Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6

hints:
1 Start DFS from the node (0, 0) and follow the path till you stop.
2 When you reach a cell and cannot move anymore check that this cell is (m - 1, n - 1) or not.

analysis:
union find

TC:O(rows * cols)
SC:O(rows * cols)
"""
from typing import List

class UnionFind:
    def __init__(self, n: int):
        self.parent = list(range(n))
        self.size = [1] * n

    def find_root(self, x: int) -> int:
        root = x
        while root != self.parent[root]:
            root = self.parent[root]
        while self.parent[x] != root:
            fa = self.parent[x]
            self.parent[x] = root
            x = fa
        return root

    def union(self, a: int, b: int) -> bool:
        a_root, b_root = self.find_root(a), self.find_root(b)
        if a_root == b_root:
            return False
        if self.size[b_root] < self.size[a_root]:
            a_root, b_root = b_root, a_root
        self.parent[a_root] = b_root
        self.size[b_root] += self.size[a_root]
        self.size[a_root] = 0
        return True

class CheckIfThereIsaValidPathInaGrid:
    def hasValidPath(self, grid: List[List[int]]) -> bool:
        rows = len(grid)
        cols = len(grid[0])

        uf = UnionFind(rows * cols)

        def getId(r, c):
            return r * cols + c

        def detectL(r, c):
            if c >= 1 and grid[r][c - 1] in [1, 4, 6]:
                uf.union(getId(r, c), getId(r, c - 1))

        def detectR(r, c):
            if c + 1 < cols and grid[r][c + 1] in [1, 3, 5]:
                uf.union(getId(r, c), getId(r, c + 1))

        def detectU(r, c):
            if r - 1 >= 0 and grid[r - 1][c] in [2, 3, 4]:
                uf.union(getId(r, c), getId(r - 1, c))

        def detectD(r, c):
            if r + 1 < rows and grid[r + 1][c] in [2, 5, 6]:
                uf.union(getId(r, c), getId(r + 1, c))

        def handle(r, c):
            if grid[r][c] == 1:
                detectL(r, c)
                detectR(r, c)
            elif grid[r][c] == 2:
                detectU(r, c)
                detectD(r, c)
            elif grid[r][c] == 3:
                detectL(r, c)
                detectD(r, c)
            elif grid[r][c] == 4:
                detectR(r, c)
                detectD(r, c)
            elif grid[r][c] == 5:
                detectL(r, c)
                detectU(r, c)
            else:
                detectR(r, c)
                detectU(r, c)

        for r in range(rows):
            for c in range(cols):
                handle(r, c)
        return not uf.union(getId(0, 0), getId(rows - 1, cols - 1))

