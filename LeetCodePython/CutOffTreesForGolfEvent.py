"""
You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:

0 means the cell cannot be walked through.
1 represents an empty cell that can be walked through.
A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.

You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).

Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.

Note: The input is generated such that no two trees have the same height, and there is at least one tree needs to be cut off.


Example 1:
Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
Output: 6
Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.

Example 2:
Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
Output: -1
Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.

Example 3:
Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
Output: 6
Explanation: You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.

Constraints:
m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109
Heights of all trees are distinct.
"""
import collections
import heapq
from typing import List


class Solution:
    def cutOffTree(self, forest: List[List[int]]) -> int:
        rows, cols, res = len(forest), len(forest[0]), 0
        dirs = [0, 1, 0, -1, 0]
        trees = sorted((height, r, c) for r, row in enumerate(forest) for c, height in enumerate(row) if height > 1)

        def dist(sr, sc, tr, tc):
            steps = 0
            visited = {(sr, sc)}
            q = collections.deque([(sr, sc)])
            while q:
                sz = len(q)
                for _ in range(sz):
                    r, c = q.popleft()
                    if r == tr and c == tc:
                        return steps
                    for i in range(len(dirs) - 1):
                        nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                        if 0 <= nb_r < rows and 0 <= nb_c < cols \
                                and forest[nb_r][nb_c] > 0 and (nb_r, nb_c) not in visited:
                            visited.add((nb_r, nb_c))
                            q.append((nb_r, nb_c))
                steps += 1
            return -1

        sr, sc = 0, 0
        for _, r, c in trees:
            d = dist(sr, sc, r, c)
            if d < 0:
                return -1
            res += d
            sr, sc = r, c
        return res