"""
This is an interactive problem.

You are given a robot in a hidden grid, and it wants to go to a target cell in this grid. The grid is of size m x n, and each cell in the grid can be empty or blocked. It is guaranteed that the start point and the robot’s destination are different, and neither of them is blocked.

You want to find the robot’s minimum distance to the target cell. However, you do not know the grid’s dimensions, or the starting point of the robot, or its target destination. You are only allowed to ask queries to your GridMaster object.

You are given a class GridMaster which you can call the following functions from:

boolean GridMaster.canMove(char direction) returns true if the robot can move in that direction. Otherwise, it returns false.
void GridMaster.move(char direction) moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, it will be ignored, and the robot would remain in the same position.
boolean GridMaster.isTarget() returns true if the robot is currently on the target cell. Otherwise, it returns false.
Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions up, down, left, and right, respectively.

Return the minimum distance between the robot’s initial starting cell and the target cell if there is a path between them. Otherwise, return -1.

Custom testing:

The test input is read as a 2D matrix grid of size m x n where:

grid[i][j] == -1 indicates that the robot is in cell (i, j).
grid[i][j] == 0 indicates that the cell (i, j) is blocked.
grid[i][j] == 1 indicates that the cell (i, j) is empty.
grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.

Example 1:
Input: grid = [[1,2],[-1,0]]

Output: 2
Explanation: One possible interaction is described below: The robot is initially standing on cell (1, 0), denoted by the -1.

master.canMove(‘U’) returns True.
master.canMove(‘D’) returns False.
master.canMove(‘L’) returns False.
master.canMove(‘R’) returns False.
master.move(‘U’) moves the robot to the cell (0, 0).
master.isTarget() returns False.
master.canMove(‘U’) returns False.
master.canMove(‘D’) returns True.
master.canMove(‘L’) returns False.
master.canMove(‘R’) returns True.
master.move(‘R’) moves the robot to the cell (0, 1).
master.isTarget() returns True.
We now know that the target is the cell (0, 1), and the shortest path to the target is 2.

Example 2:
Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]

Output: 4
Explanation: The minimum distance between the robot and the target is 4.

Example 3:
Input: grid = [[-1,0],[0,2]]

Output: -1
Explanation: There is no path from the robot to the target cell.

Constraints:
m == grid.length
n == grid[i].length
1 <= n, m <= 500
grid[i][j] is either -1, 0, 1, or 2.
There is exactly one -1 in grid.
There is exactly one 2 in grid.

"""
from collections import deque

"""
 This is GridMaster's API interface.
 You should not implement it, or speculate about its implementation
 """
class GridMaster:
   def canMove(self, direction: str) -> bool:


   def move(self, direction: str) -> bool:


   def isTarget(self) -> None:


class ShortestPathInaHiddenGrid:
    def findShortestPath(self, master: 'GridMaster') -> int:
        dirs = [('U', 'D', 0, 1), ('D', 'U', 0, -1), ('L', 'R', -1, 0), ('R', 'L', 1, 0)]
        grid = {} # Stores discovered cells and their types (0: empty, 1: target, -1: blocked)
        target = None

        def dfs(r, c):
            nonlocal target
            if master.isTarget():
                target = (r, c)
                grid[(r, c)] = 1
                return
            grid[(r, c)] = 0
            for dir, opposite_dir, dr, dc in dirs:
                nb_r = r + dr
                nb_c = c + dc
                if (nb_r, nb_c) not in grid:
                    if master.canMove(dir):
                        master.move(dir)
                        dfs(nb_r, nb_c)
                        master.move(opposite_dir)
                    else:
                        grid[(nb_r, nb_c)] = -1


        dfs(0, 0) # hypothesis start position
        if not target:
            return -1
        q = deque([(0, 0, 0)])
        visited = set()
        while q:
            r, c, dist = q.popleft()
            if (r, c) == target:
                return dist
            for _, _, dr, dc in dirs:
                nb_r = r + dr
                nb_c = c + dc
                if (nb_r, nb_c) in grid and grid[(nb_r, nb_c)] != -1 and (nb_r, nb_c) not in visited:
                    visited.add((nb_r, nb_c))
                    q.append((nb_r, nb_c, dist + 1))
        return -1

