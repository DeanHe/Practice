"""
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
"""
import collections
from typing import List


class ZeroOneMatrix:
    def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
        dirs = [0, 1, 0, -1, 0]
        rows, cols = len(mat), len(mat[0])
        q = collections.deque()
        for r in range(rows):
            for c in range(cols):
                if mat[r][c] == 0:
                    q.append((r, c))
                else:
                    mat[r][c] = -1
        while q:
            r, c = q.popleft()
            for i in range(len(dirs) - 1):
                nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                if 0 <= nb_r < rows and 0 <= nb_c < cols and mat[nb_r][nb_c] == -1:
                    mat[nb_r][nb_c] = mat[r][c] + 1
                    q.append((nb_r, nb_c))
        return mat

