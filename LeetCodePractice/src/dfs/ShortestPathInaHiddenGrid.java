package dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
his is an interactive problem.

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
 */
public class ShortestPathInaHiddenGrid {
    // This is the GridMaster's API interface.
    // You should not implement it, or speculate about its implementation
    private class GridMaster {
        boolean canMove(char direction) {
            return false;
        }

        void move(char direction) {
            return;
        }

        boolean isTarget() {
            return false;
        }
    }

    static final int MAX = 500;
    char[] directionsChar = {'U', 'L', 'D', 'R'};
    int[][] directionsArr = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    boolean flag = false;

    public int findShortestPath(GridMaster master) {
        Set<String> visited1 = new HashSet<>();
        int[][] grid = new int[MAX * 2][MAX * 2];
        grid[MAX][MAX] = -1;
        dfs(MAX, MAX, visited1, grid, master);
        if (!flag)
            return -1;
        int distance = 0;
        boolean[][] visited2 = new boolean[MAX * 2][MAX * 2];
        visited2[MAX][MAX] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{MAX, MAX});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                if (grid[row][column] == 2)
                    return distance;
                for (int[] directionArr : directionsArr) {
                    int newRow = row + directionArr[0], newColumn = column + directionArr[1];
                    if (!visited2[newRow][newColumn] && grid[newRow][newColumn] != 0) {
                        visited2[newRow][newColumn] = true;
                        queue.offer(new int[]{newRow, newColumn});
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    public void dfs(int row, int column, Set<String> visited, int[][] grid, GridMaster master) {
        grid[row][column] = 1;
        if (master.isTarget()) {
            grid[row][column] = 2;
            flag = true;
        }
        for (int i = 0; i < 4; i++) {
            char directionChar = directionsChar[i];
            char oppositeDirectionChar = directionsChar[(i + 2) % 4];
            int[] directionArr = directionsArr[i];
            int newRow = row + directionArr[0], newColumn = column + directionArr[1];
            String newStr = Arrays.toString(new int[]{newRow, newColumn});
            if (!visited.contains(newStr) && master.canMove(directionChar)) {
                visited.add(newStr);
                master.move(directionChar);
                dfs(newRow, newColumn, visited, grid, master);
                master.move(oppositeDirectionChar);
            }
        }
    }
}

