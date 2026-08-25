"""
You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:

A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:

Return the matrix after applying k cyclic rotations to it.

Example 1:
Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.

Example 2:
Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 10^9

hints:
1 First, you need to consider each layer separately as an array.
2 Just cycle this array and then re-assign it.

analysis:
TC:O(rows * cols)
"""
from typing import List


class Solution:
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        rows = len(grid)
        cols = len(grid[0])
        layers = min(rows // 2, cols // 2)
        for l in range(layers):
            row_queue = []
            col_queue = []
            vals = []
            for r in range(l, rows - l - 1):  # left
                row_queue.append(r)
                col_queue.append(l)
                vals.append(grid[r][l])
            for c in range(l, cols - l - 1):  # bottom
                row_queue.append(rows - l - 1)
                col_queue.append(c)
                vals.append(grid[rows - l - 1][c])
            for r in range(rows - l - 1, l, -1):  # right
                row_queue.append(r)
                col_queue.append(cols - l - 1)
                vals.append(grid[r][cols - l - 1])
            for c in range(cols - l - 1, l, -1):  # top
                row_queue.append(l)
                col_queue.append(c)
                vals.append(grid[l][c])
            sz = len(vals)
            kk = k % sz
            for i in range(sz):
                j = (i + sz - kk) % sz
                grid[row_queue[i]][col_queue[i]] = vals[j]
        return grid

