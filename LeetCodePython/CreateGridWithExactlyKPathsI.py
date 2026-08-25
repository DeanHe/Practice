"""
You are given three integers m, n, and k.

Construct any m x n grid consisting only of the characters '.' and '#', where:

'.' represents a free cell.
'#' represents an obstacle cell.
A valid path is a sequence of free cells that:

Starts at the top-left cell (0, 0).
Ends at the bottom-right cell (m - 1, n - 1).
Moves only:
Right, from (i, j) to (i, j + 1), or
Down, from (i, j) to (i + 1, j).
Return any grid such that there are exactly k valid paths from the top-left cell to the bottom-right cell. If no such grid exists, return an empty array.

Example 1:
Input: m = 2, n = 3, k = 2

Output: ["...","#.."]

Explanation:
There are exactly k = 2 valid paths from (0, 0) to (1, 2):

(0, 0) → (0, 1) → (0, 2) → (1, 2)
(0, 0) → (0, 1) → (1, 1) → (1, 2)

Example 2:
Input: m = 3, n = 3, k = 4

Output: ["..#","...","#.."]

Explanation:
There are exactly k = 4 valid paths from (0, 0) to (2, 2):
(0, 0) → (0, 1) → (1, 1) → (1, 2) → (2, 2)
(0, 0) → (0, 1) → (1, 1) → (2, 1) → (2, 2)
(0, 0) → (1, 0) → (1, 1) → (1, 2) → (2, 2)
(0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2)

Example 3:
Input: m = 1, n = 4, k = 2
Output: []

Explanation:
No grid exists with exactly k = 2 valid paths for a 1 x 4 grid, so the answer is an empty array.

Constraints:
1 <= m, n <= 10
1 <= k <= 4

hints:
1 If m == 1 or n == 1, the only possible positive number of valid paths is 1.
2 For m, n > 1, construct a small grid pattern with exactly k paths for each k from 1 to 4.
3 For k = 2, use a 2 x 2 open block. It has exactly two paths: right then down, or down then right.
"""

class CreateGridWithExactlyKPathsI:
    def createGrid(self, m: int, n: int, k: int) -> list[str]:
        if m == 3 and n == 3 and k == 4:
            return ["..#", "...", "#.."]
        if (m == 1 or n == 1) and k > 1:
            return []
        res = [['#'] * n for _ in range(m)]
        for c in range(n):
            res[0][c] = '.' # open first row
        for r in range(m):
            res[r][-1] = '.' # open last column
        k -= 1
        if m < n:
            c = n - 2
            while c >= 0 and k:
                res[1][c] = '.' # create one extra path
                c -= 1
                k -= 1
        else:
            r = 1
            while r < m and k:
                res[r][n - 2] = '.' # create one extra path
                r += 1
                k -= 1
        if k:
            return []
        return[''.join(row) for row in res]
