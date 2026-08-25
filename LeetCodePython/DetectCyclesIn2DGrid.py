"""
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

Example 1:
Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:
Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:
Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.

hints:
1 Keep track of the parent (previous position) to avoid considering an invalid path.
2 Use DFS or BFS and keep track of visited cells to see if there is a cycle.

analysis:
DFS or Union Find
TC:O(M*N)
SC:O(M*N)
"""
from typing import List


class UnionFind:
    def __init__(self, n):
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


class DetectCyclesIn2DGrid:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        rows = len(grid)
        cols = len(grid[0])
        uf = UnionFind(rows * cols)
        for r in range(rows):
            for c in range(cols):
                # check if up cell is already united
                if r > 0 and grid[r][c] == grid[r - 1][c]:
                    if not uf.union(r * cols + c, (r - 1) * cols + c):
                        return True
                # check if up cell is already united
                if c > 0 and grid[r][c] == grid[r][c - 1]:
                    if not uf.union(r * cols + c, r * cols + c - 1):
                        return True
        return False
