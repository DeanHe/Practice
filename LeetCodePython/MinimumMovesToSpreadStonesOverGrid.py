"""
You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. The grid contains exactly 9 stones, and there can be multiple stones in a single cell.

In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.

Return the minimum number of moves required to place one stone in each cell.

Example 1:
Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
Output: 3
Explanation: One possible sequence of moves to place one stone in each cell is:
1- Move one stone from cell (2,1) to cell (2,2).
2- Move one stone from cell (2,2) to cell (1,2).
3- Move one stone from cell (1,2) to cell (0,2).
In total, it takes 3 moves to place one stone in each cell of the grid.
It can be shown that 3 is the minimum number of moves required to place one stone in each cell.

Example 2:
Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
Output: 4
Explanation: One possible sequence of moves to place one stone in each cell is:
1- Move one stone from cell (0,1) to cell (0,2).
2- Move one stone from cell (0,1) to cell (1,1).
3- Move one stone from cell (2,2) to cell (1,2).
4- Move one stone from cell (2,2) to cell (2,1).
In total, it takes 4 moves to place one stone in each cell of the grid.
It can be shown that 4 is the minimum number of moves required to place one stone in each cell.


Constraints:
grid.length == grid[i].length == 3
0 <= grid[i][j] <= 9
Sum of grid is equal to 9.

hints:
1 There are at most 4 cells with more than one stone.
2 Let a be the number of cells containing more than one stone, and b be the number of cells containing no stones. . b^a ≤ 6561. Use this fact to come up with a bruteforce
3 For all empty cells, bruteforce over all possible cells from which a stone can come. Note that a stone will always come from a cell containing at least 2 stones.

analysis:
backtracking
"""
from typing import List


class MinimumMovesToSpreadStonesOverGrid:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        zero_cnt = 0
        for r in range(3):
            for c in range(3):
                if grid[r][c] == 0:
                    zero_cnt += 1
        if zero_cnt == 0:
            return 0
        res = 10 ** 9
        for r in range(3):
            for c in range(3):
                if grid[r][c] == 0:
                    for nb_r in range(3):
                        for nb_c in range(3):
                            if nb_r != r or nb_c != c:
                                if grid[nb_r][nb_c] > 1:
                                    moves = abs(r - nb_r) + abs(c - nb_c)
                                    grid[nb_r][nb_c] -= 1
                                    grid[r][c] += 1
                                    res = min(res, moves + self.minimumMoves(grid))
                                    grid[r][c] -= 1
                                    grid[nb_r][nb_c] += 1
        return res


