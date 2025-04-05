"""
You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].

Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].

Return the smallest index i at which either a row or a column will be completely painted in mat.

Example 1:
image explanation for example 1
Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
Output: 2
Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].

Example 2:
image explanation for example 2
Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
Output: 3
Explanation: The second column becomes fully painted at arr[3].

Constraints:
m == mat.length
n = mat[i].length
arr.length == m * n
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= arr[i], mat[r][c] <= m * n
All the integers of arr are unique.
All the integers of mat are unique.

hints:
1 Can we use a frequency array?
2 Pre-process the positions of the values in the matrix.
3 Traverse the array and increment the corresponding row and column frequency using the pre-processed positions.
4 If the row frequency becomes equal to the number of columns, or vice-versa return the current index.

Analysis:
TC: O(N)
"""
from typing import List


class FirstCompletelyPaintedRowOrColumn:
    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        rows, cols = len(mat), len(mat[0])
        rows_cnt = [0] * rows
        cols_cnt = [0] * cols
        idx = {}
        for r in range(rows):
            for c in range(cols):
                idx[mat[r][c]] = (r, c)
        for i, num in enumerate(arr):
            r, c = idx[num]
            rows_cnt[r] += 1
            cols_cnt[c] += 1
            if rows_cnt[r] == cols or cols_cnt[c] == rows:
                return i
        return -1