"""
Given a 0-indexed 2D grid of size m x n, you should find the matrix answer of size m x n.

The value of each cell (r, c) of the matrix answer is calculated in the following way:

Let topLeft[r][c] be the number of distinct values in the top-left diagonal of the cell (r, c) in the matrix grid.
Let bottomRight[r][c] be the number of distinct values in the bottom-right diagonal of the cell (r, c) in the matrix grid.
Then answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|.

Return the matrix answer.

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end.

A cell (r1, c1) belongs to the top-left diagonal of the cell (r, c), if both belong to the same diagonal and r1 < r. Similarly is defined bottom-right diagonal.

Example 1:
Input: grid = [[1,2,3],[3,1,5],[3,2,1]]
Output: [[1,1,0],[1,0,1],[0,1,1]]
Explanation: The 1st diagram denotes the initial grid.
The 2nd diagram denotes a grid for cell (0,0), where blue-colored cells are cells on its bottom-right diagonal.
The 3rd diagram denotes a grid for cell (1,2), where red-colored cells are cells on its top-left diagonal.
The 4th diagram denotes a grid for cell (1,1), where blue-colored cells are cells on its bottom-right diagonal and red-colored cells are cells on its top-left diagonal.
- The cell (0,0) contains [1,1] on its bottom-right diagonal and [] on its top-left diagonal. The answer is |1 - 0| = 1.
- The cell (1,2) contains [] on its bottom-right diagonal and [2] on its top-left diagonal. The answer is |0 - 1| = 1.
- The cell (1,1) contains [1] on its bottom-right diagonal and [1] on its top-left diagonal. The answer is |1 - 1| = 0.
The answers of other cells are similarly calculated.

Example 2:
Input: grid = [[1]]
Output: [[0]]
Explanation: - The cell (0,0) contains [] on its bottom-right diagonal and [] on its top-left diagonal. The answer is |0 - 0| = 0.


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n, grid[i][j] <= 50
"""
from collections import defaultdict
from typing import List


class DifferenceOfNumberOfDistinctValuesOnDiagonals:
    def differenceOfDistinctValues(self, grid: List[List[int]]) -> List[List[int]]:
        rows, cols = len(grid), len(grid[0])
        res = [[0] * cols for _ in range(rows)]
        d = defaultdict(set)
        for r in range(rows):
            for c in range(cols):
                if r > 0 and c > 0:
                    res[r][c] += len(d[r - c])
                d[r - c].add(grid[r][c])
        d.clear()
        for r in range(rows - 1, -1, -1):
            for c in range(cols - 1, -1, -1):
                if r < rows - 1 and c < cols - 1:
                    res[r][c] = abs(res[r][c] - len(d[r - c]))
                d[r - c].add(grid[r][c])
        return res